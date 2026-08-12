package com.saigontechnologyintern.document_management;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class DocumentManagerService {
    private DocumentManagerRepostory docManRepo;

    public DocumentManagerService(DocumentManagerRepostory docManRepo) {
        this.docManRepo = docManRepo;
    }

    public List<DocumentManager> getAllDocuments(){
        return docManRepo.findAll();
    }
    public DocumentManager getDocumentsById(Integer id){
        return docManRepo.findById(id).orElseThrow(() -> new IllegalStateException(id + "Not Found"));
    }

    public void insertDocument(DocumentManager dm){
       docManRepo.save(dm);
    }
    public void updateDocument(Integer id, DocumentManager dm) {
        // Kiểm tra xem document có tồn tại không
        DocumentManager existingDoc = docManRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException("Document with id " + id + " does not exist"));

        // Cập nhật các field cần thiết
        existingDoc.setName(dm.getName());
        // Nếu DocumentManager có thêm field khác, bạn cũng set tương tự ở đây

        // Lưu lại vào database
        docManRepo.save(existingDoc);
    }
    public void deleteDocumentById(Integer id){
//        docManRepo.findById(id);
       boolean exists = docManRepo.existsById(id);
       if(!exists){
           throw new IllegalStateException("document with id: " + id + "does not exist");
       }
        docManRepo.deleteById(id);
    }
}
