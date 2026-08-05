//package com.saigontechnologyintern.document_management;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
//import com.saigontechnologyintern.document_management.documentManagement.DocumentManageRepository;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManager;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManagerRepository;
//import com.saigontechnologyintern.document_management.sharingRequestManagement.SharingRequestManager;
//import com.saigontechnologyintern.document_management.sharingRequestManagement.SharingRequestManagerRepository;
//import com.saigontechnologyintern.document_management.sharingRequestManagement.SharingRequestManagerService;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import com.saigontechnologyintern.document_management.userManagement.UserManagerRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class SharingRequestManagerServiceTest {
//
//    @Mock private SharingRequestManagerRepository sharingRepo;
//    @Mock private DocumentManageRepository documentRepo;
//    @Mock private UserManagerRepository userRepo;
//    @Mock private PermissionManagerRepository permissionRepo;
//
//    @InjectMocks private SharingRequestManagerService service;
//
//    private DocumentManage doc;
//    private UserManager owner;
//    private UserManager recipient;
//
//    @BeforeEach
//    void setUp() {
//        owner = new UserManager();
//        owner.setUserId(1);
//        recipient = new UserManager();
//        recipient.setUserId(2);
//        doc = new DocumentManage();
//        doc.setDocId(10);
//        doc.setOwner(owner);
//    }
//
//    @Test
//    void createSharingRequest_ownerCanCreate() {
//        when(documentRepo.findById(10)).thenReturn(Optional.of(doc));
//        when(userRepo.findById(2)).thenReturn(Optional.of(recipient));
//        when(sharingRepo.save(any(SharingRequestManager.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        SharingRequestManager created = service.createSharingRequest(10, 2, "Editor", 1);
//
//        assertThat(created.getStatus()).isEqualTo("Pending");
//        assertThat(created.getRequester().getUserId()).isEqualTo(2);
//    }
//
//    @Test
//    void approveSharingRequest_recipientOnly() {
//        SharingRequestManager req = new SharingRequestManager();
//        req.setRequestId(5);
//        req.setDocument(doc);
//        req.setRequester(recipient);
//        req.setPermission("Editor");
//
//        when(sharingRepo.findById(5)).thenReturn(Optional.of(req));
//        when(sharingRepo.save(any(SharingRequestManager.class))).thenAnswer(inv -> inv.getArgument(0));
//        when(permissionRepo.findByDocument_DocIdAndUser_UserId(10, 2)).thenReturn(Optional.empty());
//
//        SharingRequestManager approved = service.approveSharingRequest(5, 2);
//
//        assertThat(approved.getStatus()).isEqualTo("Approved");
//        verify(permissionRepo).save(any(PermissionManager.class));
//    }
//
//    @Test
//    void approveSharingRequest_nonRecipientDenied() {
//        SharingRequestManager req = new SharingRequestManager();
//        req.setRequestId(5);
//        req.setRequester(recipient);
//        when(sharingRepo.findById(5)).thenReturn(Optional.of(req));
//
//        assertThatThrownBy(() -> service.approveSharingRequest(5, 99))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Only recipient");
//    }
//}
