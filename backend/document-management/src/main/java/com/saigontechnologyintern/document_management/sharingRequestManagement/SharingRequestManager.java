package com.saigontechnologyintern.document_management.sharingRequestManagement;

import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
import com.saigontechnologyintern.document_management.userManagement.UserManager;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
//C:\Users\HELLO\AppData\Local\Programs\Eclipse Adoptium\jdk-21.0.11.10-hotspot\
@Entity
@Table(name = "sharing_requests")
public class SharingRequestManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    @Column(length = 20)
    private String status;

    @Column(name = "requested_at")
    private LocalDateTime requestedAt;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private DocumentManage document;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserManager requester;

    // equals, hashCode, getters, setters
}
