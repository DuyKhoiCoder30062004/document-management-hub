package com.saigontechnologyintern.document_management.folderManagement;

import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
import com.saigontechnologyintern.document_management.userManagement.UserManager;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "folders")
public class FolderManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Integer folderId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserManager owner;

    @OneToMany(mappedBy = "folder")
    private List<DocumentManage> documents;

    // equals, hashCode, getters, setters
}
