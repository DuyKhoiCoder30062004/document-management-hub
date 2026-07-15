package com.saigontechnologyintern.document_management.userManagement;

import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
import com.saigontechnologyintern.document_management.folderManagement.FolderManager;
import com.saigontechnologyintern.document_management.permissionManagement.PermissionManager;
import com.saigontechnologyintern.document_management.sharingRequestManagement.SharingRequestManager;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "users")
public class UserManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Relationships
    @OneToMany(mappedBy = "owner")
    private List<DocumentManage> documents;

    @OneToMany(mappedBy = "owner")
    private List<FolderManager> folders;

    @OneToMany(mappedBy = "user")
    private List<PermissionManager> permissions;

    @OneToMany(mappedBy = "requester")
    private List<SharingRequestManager> sharingRequests;

    // equals, hashCode, getters, setters
}
