package cl.duoc.rodrcruz.usersellermodule.controller;

import cl.duoc.rodrcruz.usersellermodule.controller.request.AuthRequest;
import cl.duoc.rodrcruz.usersellermodule.controller.request.RegisterRequest;
import cl.duoc.rodrcruz.usersellermodule.controller.response.AuthResponse;
import cl.duoc.rodrcruz.usersellermodule.repository.RoleDB;
import cl.duoc.rodrcruz.usersellermodule.repository.SellerDB;
import cl.duoc.rodrcruz.usersellermodule.repository.UserDB;
import cl.duoc.rodrcruz.usersellermodule.security.JwtUtil;
import cl.duoc.rodrcruz.usersellermodule.service.SellerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private SellerService sellerService;

    private AuthRequest authRequestOk;
    private AuthResponse authResponseOk;
    private RegisterRequest registerRequestOk;
    private SellerDB registeredSellerDB;
    @BeforeEach
    void setUp() {
        authRequestOk = new AuthRequest("testuser@example.com", "password123");
        authResponseOk = new AuthResponse("mocked.jwt.token", "Login exitoso"); // Coincide con tu constructor AuthResponse

        registerRequestOk = new RegisterRequest();
        registerRequestOk.setName("Nuevo");
        registerRequestOk.setLastname("Vendedor");
        registerRequestOk.setEmail("nuevoseller@example.com");
        registerRequestOk.setPassword("sellerPass");
        registerRequestOk.setPhone("123456789");
        registerRequestOk.setAge(30);

        // SellerDB para el mock del servicio (con la contraseña en claro, como parece devolver tu SellerService)
        // Ajusta el constructor y los setters de SellerDB si son diferentes
        RoleDB sellerRole = new RoleDB(2, "VENDEDOR"); // Asumiendo que RoleDB tiene un constructor así
        registeredSellerDB = new SellerDB();
        registeredSellerDB.setId(101);
        registeredSellerDB.setName(registerRequestOk.getName());
        registeredSellerDB.setLastname(registerRequestOk.getLastname());
        registeredSellerDB.setEmail(registerRequestOk.getEmail());
        registeredSellerDB.setPassword(registerRequestOk.getPassword()); // O la contraseña hasheada si tu servicio devuelve eso
        registeredSellerDB.setPhone(registerRequestOk.getPhone());
        registeredSellerDB.setAge(registerRequestOk.getAge());
        registeredSellerDB.setRole(sellerRole);
    }

    @Test
    void authenticateUser()throws Exception {// 1. Mock de AuthenticationManager: simula una autenticación exitosa
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequestOk.getUsername(), authRequestOk.getPassword(), Collections.emptyList() // Colección vacía de authorities
        );
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        // 2. Mock de UserDetails: simula los detalles del usuario autenticado
        UserDetails userDetails = new User(authRequestOk.getUsername(), authRequestOk.getPassword(), Collections.emptyList());        when(authentication.getPrincipal()).thenReturn(userDetails);

        // 3. Mock de JwtUtil: simula la generación del token JWT
        when(jwtUtil.generateToken(any(UserDetails.class))).thenReturn(authResponseOk.getToken());

        // Realizar la petición POST
        mockMvc.perform(post("/api/auth/login") // Endpoint real
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequestOk)))
                .andExpect(status().isOk()) // Espera 200 OK
                .andExpect(jsonPath("$.jwt").value(authResponseOk.getToken())) // Verifica el token en el JSON
                .andExpect(jsonPath("$.message").value(authResponseOk.getMessage())); // Verifica el mensaje en el JSON
    }

    @Test
    void registerSeller() {
    }
}