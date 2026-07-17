package com.saigontechnologyintern.document_management;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/document-manager")
@CrossOrigin(origins = "http://localhost:5173")
public class DocumentManagerController {
    private final DocumentManagerService docManService;

    public DocumentManagerController(DocumentManagerService docManService) {
        this.docManService = docManService;
    }

    //    @GetMapping
//    public List<DocumentManager> getDocumentManagers(){
//        return List.of(new DocumentManager(1,"a"),new DocumentManager(2,"b"));
//    }
    @GetMapping
    public List<DocumentManager> getDocuments(){
        return docManService.getAllDocuments();
    }
    @GetMapping("{id}")
    public DocumentManager getDocumentsById(@PathVariable Integer id){
        return docManService.getDocumentsById(id);
    }

    @PostMapping
    public void addNewDocument(@RequestBody DocumentManager dm){
       docManService.insertDocument(dm);
    }
    //Giờ cần PutMapping, DeleteMapping => Hoàn thành CRUD
    @PutMapping(path = "{id}")
    public void updateDocument(
            @PathVariable Integer id,
            @RequestBody DocumentManager dm) {
        docManService.updateDocument(id, dm);
    }
    @DeleteMapping(path = "{id}")
    //public void deleteDocument(@PathVariable("id") Integer id)
    public void deleteDocument(@PathVariable Integer id){
        docManService.deleteDocumentById(id);
    }
}
