package com.saigontechnologyintern.document_management.permissionManagement;

import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
import com.saigontechnologyintern.document_management.userManagement.UserManager;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permissions")
public class PermissionManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perm_id")
    private Integer permId;

    @Column(name = "access_type", nullable = false, length = 20)
    private String accessType;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private DocumentManage document;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserManager user;

    // equals, hashCode, getters, setters
}
