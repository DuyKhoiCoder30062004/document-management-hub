package com.saigontechnologyintern.document_management.documentManagement;

import com.saigontechnologyintern.document_management.authManagement.AuthService;
import com.saigontechnologyintern.document_management.authManagement.JwtService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/documents")
@CrossOrigin(origins = "*")
public class DocumentManageController {
    private final DocumentManageService documentManageService;
    private final AuthService authService;
    private final JwtService jwtService;

    public DocumentManageController(
            DocumentManageService documentManageService,
            AuthService authService,
            JwtService jwtService) {
        this.documentManageService = documentManageService;
        this.authService = authService;
        this.jwtService = jwtService;
    }

    private String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authorization header");
        }
        String token = authHeader.substring(7).trim();
        if (!jwtService.isTokenValid(token)) {
            throw new IllegalArgumentException("Invalid or expired token");
        }
        return token;
    }

    private Integer currentUserIdFromHeader(String authorization) {
        String token = extractToken(authorization);
        return authService.getCurrentUser(token).getUserId();
    }

    @GetMapping
    public List<DocumentManage> getDocuments(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(value = "folder_id", required = false) Integer folderId,
            @RequestParam(value = "q", required = false) String q) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.getDocuments(currentUserId, folderId, q);
    }

    @GetMapping("/search")
    public List<DocumentManage> searchDocuments(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("q") String q) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.searchDocuments(currentUserId, q);
    }

    @GetMapping("/{id}")
    public DocumentManage getDocumentById(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.getDocumentById(id, currentUserId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public DocumentManage createDocument(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "folder_id", required = false) Integer folderId,
            @RequestParam(value = "title", required = false) String title) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.createDocument(file, folderId, title, currentUserId);
    }

    @PatchMapping("/{id}")
    public DocumentManage updateDocument(
            @PathVariable Integer id,
            @RequestBody DocumentManage document,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.updateDocument(id, document, currentUserId);
    }

    @PatchMapping("/{id}/move")
    public DocumentManage moveDocument(
            @PathVariable Integer id,
            @RequestBody MoveDocumentRequest request,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        return documentManageService.moveDocument(id, request.getFolder_id(), currentUserId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        documentManageService.deleteDocumentById(id, currentUserId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadDocument(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        DocumentManage doc = documentManageService.getDocumentById(id, currentUserId);

        byte[] content = doc.getFileData();
        if (content == null || content.length == 0) {
            throw new IllegalArgumentException("No file content stored for document id: " + id);
        }

        String filename = (doc.getOriginalFilename() != null && !doc.getOriginalFilename().isBlank())
                ? doc.getOriginalFilename()
                : doc.getTitle();

        String contentType = (doc.getContentType() != null && !doc.getContentType().isBlank())
                ? doc.getContentType()
                : "application/octet-stream";

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(content);
    }

    @GetMapping("/{id}/audit")
    public List<String> auditLog(
            @PathVariable Integer id,
            @RequestHeader("Authorization") String authorization) {

        Integer currentUserId = currentUserIdFromHeader(authorization);
        documentManageService.getDocumentById(id, currentUserId);
        return List.of("placeholder-audit");
    }

    public static class MoveDocumentRequest {
        private Integer folder_id;

        public Integer getFolder_id() {
            return folder_id;
        }

        public void setFolder_id(Integer folder_id) {
            this.folder_id = folder_id;
        }
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<java.util.Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        String message = ex.getMessage() == null ? "Bad request" : ex.getMessage();

        // Permission-style failures become 403 for easier frontend diagnosis
        if (message.startsWith("Move denied") || message.startsWith("Upload denied")
                || message.contains("do not have permission") || message.contains("only upload") || message.contains("only move")) {
            return ResponseEntity.status(403).body(java.util.Map.of("message", message));
        }

        return ResponseEntity.badRequest().body(java.util.Map.of("message", message));
    }
}
