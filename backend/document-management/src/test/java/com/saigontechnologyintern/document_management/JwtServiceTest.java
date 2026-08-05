//package com.saigontechnologyintern.document_management;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//import com.saigontechnologyintern.document_management.authManagement.JwtService;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.test.util.ReflectionTestUtils;
//
//class JwtServiceTest {
//
//    private JwtService jwtService;
//
//    @BeforeEach
//    void setUp() {
//        jwtService = new JwtService();
//        // 32-byte base64 key: "abcdefghijklmnopqrstuvwxyz123456"
//        ReflectionTestUtils.setField(jwtService, "jwtSecret", "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXoxMjM0NTY=");
//        ReflectionTestUtils.setField(jwtService, "jwtExpirationMs", 3_600_000L);
//    }
//
//    @Test
//    void generateAndExtract_success() {
//        UserManager user = new UserManager("Alice", "alice@test.com", "hash", "User");
//        user.setUserId(123);
//
//        String token = jwtService.generateToken(user);
//
//        assertThat(token).isNotBlank();
//        assertThat(jwtService.extractUserId(token)).isEqualTo(123);
//        assertThat(jwtService.extractRole(token)).isEqualTo("USER");
//        assertThat(jwtService.isTokenValid(token)).isTrue();
//    }
//
//    @Test
//    void invalidToken_returnsFalse() {
//        assertThat(jwtService.isTokenValid("not-a-jwt")).isFalse();
//    }
//
//    @Test
//    void invalidSubject_throws() {
//        UserManager user = new UserManager("Alice", "alice@test.com", "hash", "User");
//        user.setUserId(1);
//        String token = jwtService.generateToken(user);
//        // Use wrong key to generate parse failure path by swapping service key.
//        ReflectionTestUtils.setField(jwtService, "jwtSecret", "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=");
//
//        assertThat(jwtService.isTokenValid(token)).isFalse();
//    }
//}
