package com.saigontechnologyintern.document_management.folderManagement;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/folders")
@CrossOrigin(origins = "*")
public class FolderManagerController {
    private final FolderManagerService folderManagerService;

    public FolderManagerController(FolderManagerService folderManagerService) {
        this.folderManagerService = folderManagerService;
    }

    @GetMapping
    public List<FolderManager> getAllFolders() {
        return folderManagerService.getAllFolders();
    }

    @GetMapping("/{id}")
    public FolderManager getFolderById(@PathVariable Integer id) {
        return folderManagerService.getFolderById(id);
    }

    @PostMapping
    public FolderManager createFolder(@RequestBody FolderManager folder) {
        return folderManagerService.createFolder(folder);
    }

    @PatchMapping("/{id}")
    public FolderManager updateFolder(@PathVariable Integer id, @RequestBody FolderManager folder) {
        return folderManagerService.updateFolder(id, folder);
    }

    @DeleteMapping("/{id}")
    public void deleteFolder(@PathVariable Integer id) {
        folderManagerService.deleteFolderById(id);
    }
}
