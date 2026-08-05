////package com.saigontechnologyintern.document_management;
//
////Integration test
//
//
//package com.saigontechnologyintern.document_management;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.saigontechnologyintern.document_management.authManagement.*;
//import com.saigontechnologyintern.document_management.userManagement.UserManager;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
////import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
////@WebMvcTest(AuthController.class)
//class AuthControllerWebMvcTest {
//
//    //@MockBean
//    private MockMvc mockMvc;
//    //@MockBean
//    private ObjectMapper objectMapper;
//
//    private AuthService authService;
//    private JwtService jwtService;
//
//    @Test
//    void register_success() throws Exception {
//        UserManager user = new UserManager();
//        user.setUserId(1);
//        user.setEmail("a@test.com");
//        AuthResponseDto dto = new AuthResponseDto("token", user);
//        when(authService.register(any(RegisterRequest.class))).thenReturn(dto);
//
//        mockMvc.perform(post("/api/v1/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new RegisterRequest("A", "a@test.com", "pw"))))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").value("token"));
//    }
//
//    @Test
//    void login_invalid_returns401() throws Exception {
//        when(authService.login(any(LoginRequest.class))).thenThrow(new IllegalArgumentException("Invalid email or password"));
//
//        mockMvc.perform(post("/api/v1/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new LoginRequest("x@test.com", "pw"))))
//                .andExpect(status().isUnauthorized())
//                .andExpect(jsonPath("$.message").exists());
//    }
//
//    @Test
//    void me_validToken_returnsUser() throws Exception {
//        UserManager user = new UserManager();
//        user.setUserId(1);
//        user.setEmail("a@test.com");
//
//        when(jwtService.isTokenValid("abc")).thenReturn(true);
//        when(authService.getCurrentUser("abc")).thenReturn(user);
//
//        mockMvc.perform(get("/api/v1/auth/me").header("Authorization", "Bearer abc"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").value(1));
//    }
//}
//
