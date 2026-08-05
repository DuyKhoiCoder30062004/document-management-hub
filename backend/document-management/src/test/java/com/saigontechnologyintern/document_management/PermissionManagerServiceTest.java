//package com.saigontechnologyintern.document_management;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import com.saigontechnologyintern.document_management.documentManagement.DocumentManage;
//import com.saigontechnologyintern.document_management.documentManagement.DocumentManageRepository;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManager;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManagerRepository;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManagerService;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import com.saigontechnologyintern.document_management.userManagement.UserManagerRepository;
//import java.util.Optional;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class PermissionManagerServiceTest {
//
//    @Mock private PermissionManagerRepository permissionManagerRepository;
//    @Mock private DocumentManageRepository documentManageRepository;
//    @Mock private UserManagerRepository userManagerRepository;
//
//    @InjectMocks private PermissionManagerService service;
//
//    @Test
//    void updatePermission_editorShouldStayEditor() {
//        PermissionManager permission = new PermissionManager();
//        permission.setPermId(1);
//        permission.setAccessType("Viewer");
//
//        when(permissionManagerRepository.findById(1)).thenReturn(Optional.of(permission));
//        when(permissionManagerRepository.save(any(PermissionManager.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        PermissionManager updated = service.updatePermission(1, "Editor");
//
//        // This should pass after normalizeAccessType bug is fixed to lower-case mapping.
//        assertThat(updated.getAccessType()).isEqualTo("Editor");
//    }
//
//    @Test
//    void createPermission_success() {
//        DocumentManage doc = new DocumentManage();
//        doc.setDocId(10);
//        UserManager user = new UserManager();
//        user.setUserId(2);
//
//        when(documentManageRepository.findById(10)).thenReturn(Optional.of(doc));
//        when(userManagerRepository.findById(2)).thenReturn(Optional.of(user));
//        when(permissionManagerRepository.save(any(PermissionManager.class))).thenAnswer(inv -> inv.getArgument(0));
//
//        PermissionManager created = service.createPermission(10, 2, "Viewer");
//        assertThat(created.getDocument().getDocId()).isEqualTo(10);
//        assertThat(created.getUser().getUserId()).isEqualTo(2);
//    }
//}
