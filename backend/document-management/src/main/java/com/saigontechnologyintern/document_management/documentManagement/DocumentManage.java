package com.saigontechnologyintern.document_management.documentManagement;

import com.saigontechnologyintern.document_management.folderManagement.FolderManager;
import com.saigontechnologyintern.document_management.permissionManagement.PermissionManager;
import com.saigontechnologyintern.document_management.sharingRequestManagement.SharingRequestManager;
import com.saigontechnologyintern.document_management.userManagement.UserManager;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "documents")
public class DocumentManage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Integer docId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "jsonb")
    private String metadata;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserManager owner;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    private FolderManager folder;

    @OneToMany(mappedBy = "document")
    private List<PermissionManager> permissions;

    @OneToMany(mappedBy = "document")
    private List<SharingRequestManager> sharingRequests;

    // equals, hashCode, getters, setters
}
