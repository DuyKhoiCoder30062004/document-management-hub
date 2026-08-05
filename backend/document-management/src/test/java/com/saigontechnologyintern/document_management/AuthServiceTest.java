//package com.saigontechnologyintern.document_management;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.saigontechnologyintern.document_management.authManagement.*;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import com.saigontechnologyintern.document_management.userManagement.UserManagerRepository;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@ExtendWith(MockitoExtension.class)
//class AuthServiceTest {
//
//    @Mock private UserManagerRepository userRepository;
//    @Mock private JwtService jwtService;
//    @Mock private PasswordEncoder passwordEncoder;
//
//    @InjectMocks private AuthService authService;
//
//    private RegisterRequest registerRequest;
//    private LoginRequest loginRequest;
//    private UserManager existingUser;
//
//    @BeforeEach
//    void setUp() {
//        registerRequest = new RegisterRequest("Alice", "alice@test.com", "secret");
//        loginRequest = new LoginRequest("alice@test.com", "secret");
//
//        existingUser = new UserManager("Alice", "alice@test.com", "hashed", "User");
//        existingUser.setUserId(1);
//        existingUser.setCreatedAt(LocalDateTime.now());
//    }
//
//    @Test
//    void register_success() {
//        when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.empty());
//        when(passwordEncoder.encode("secret")).thenReturn("hashed");
//        when(jwtService.generateToken(any(UserManager.class))).thenReturn("jwt-token");
//
//        AuthResponseDto result = authService.register(registerRequest);
//
//        assertThat(result.getToken()).isEqualTo("jwt-token");
//        assertThat(result.getUser().getEmail()).isEqualTo("alice@test.com");
//        verify(userRepository).save(any(UserManager.class));
//    }
//
//    @Test
//    void register_duplicateEmail_throws() {
//        when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.of(existingUser));
//
//        assertThatThrownBy(() -> authService.register(registerRequest))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Email already registered");
//    }
//
//    @Test
//    void login_success() {
//        when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.of(existingUser));
//        when(passwordEncoder.matches("secret", "hashed")).thenReturn(true);
//        when(jwtService.generateToken(existingUser)).thenReturn("jwt-token");
//
//        AuthResponseDto result = authService.login(loginRequest);
//
//        assertThat(result.getToken()).isEqualTo("jwt-token");
//        assertThat(result.getUser().getUserId()).isEqualTo(1);
//    }
//
//    @Test
//    void login_wrongPassword_throws() {
//        when(userRepository.findByEmail("alice@test.com")).thenReturn(Optional.of(existingUser));
//        when(passwordEncoder.matches("secret", "hashed")).thenReturn(false);
//
//        assertThatThrownBy(() -> authService.login(loginRequest))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Invalid email or password");
//    }
//
//    @Test
//    void getCurrentUser_success() {
//        when(jwtService.extractUserId("jwt-token")).thenReturn(1);
//        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
//
//        UserManager result = authService.getCurrentUser("jwt-token");
//
//        assertThat(result.getUserId()).isEqualTo(1);
//        assertThat(result.getEmail()).isEqualTo("alice@test.com");
//    }
//}
