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
//import com.saigontechnologyintern.document_management.documentManagement.DocumentManageService;
//import com.saigontechnologyintern.document_management.folderManagement.FolderManager;
//import com.saigontechnologyintern.document_management.folderManagement.FolderManagerRepository;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManager;
//import com.saigontechnologyintern.document_management.permissionManagement.PermissionManagerRepository;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import com.saigontechnologyintern.document_management.userManagement.UserManagerRepository;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.mock.web.MockMultipartFile;
//
//@ExtendWith(MockitoExtension.class)
//class DocumentManageServiceTest {
//
//    @Mock private DocumentManageRepository documentManageRepository;
//    @Mock private FolderManagerRepository folderManagerRepository;
//    @Mock private UserManagerRepository userManagerRepository;
//    @Mock private PermissionManagerRepository permissionManagerRepository;
//
//    @InjectMocks private DocumentManageService service;
//
//    private UserManager owner;
//    private DocumentManage doc;
//
//    @BeforeEach
//    void setUp() {
//        owner = new UserManager();
//        owner.setUserId(1);
//        owner.setRole("User");
//
//        doc = new DocumentManage();
//        doc.setDocId(10);
//        doc.setOwner(owner);
//        doc.setTitle("Spec");
//        doc.setMetadata(Map.of("mime_type", "text/plain"));
//    }
//
//    @Test
//    void getDocuments_noFilters_callsVisibleQuery() {
//        when(documentManageRepository.findVisibleDocumentsForUser(1)).thenReturn(List.of(doc));
//        List<DocumentManage> result = service.getDocuments(1, null, null);
//        assertThat(result).hasSize(1);
//        verify(documentManageRepository).findVisibleDocumentsForUser(1);
//    }
//
//    @Test
//    void getDocumentById_ownerCanView() {
//        when(documentManageRepository.findById(10)).thenReturn(Optional.of(doc));
//        assertThat(service.getDocumentById(10, 1)).isSameAs(doc);
//    }
//
//    @Test
//    void getDocumentById_userWithoutPermission_throws() {
//        when(documentManageRepository.findById(10)).thenReturn(Optional.of(doc));
//        when(permissionManagerRepository.findByDocument_DocIdAndUser_UserId(10, 2))
//                .thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> service.getDocumentById(10, 2))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Document not found");
//    }
//
//    @Test
//    void createDocument_createsOwnerPermissionRow() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("file", "a.txt", "text/plain", "hello".getBytes());
//        when(userManagerRepository.findById(1)).thenReturn(Optional.of(owner));
//        when(documentManageRepository.save(any(DocumentManage.class))).thenAnswer(inv -> {
//            DocumentManage saved = inv.getArgument(0);
//            saved.setDocId(100);
//            return saved;
//        });
//        when(permissionManagerRepository.findByDocument_DocIdAndUser_UserId(100, 1))
//                .thenReturn(Optional.empty());
//
//        DocumentManage saved = service.createDocument(file, null, "Title", 1);
//
//        assertThat(saved.getDocId()).isEqualTo(100);
//        verify(permissionManagerRepository).save(any(PermissionManager.class));
//    }
//
//    @Test
//    void moveDocument_notOwner_throws() {
//        UserManager another = new UserManager();
//        another.setUserId(2);
//        doc.setOwner(another);
//
//        when(documentManageRepository.findById(10)).thenReturn(Optional.of(doc));
//
//        assertThatThrownBy(() -> service.moveDocument(10, 5, 1))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("permission");
//    }
//}