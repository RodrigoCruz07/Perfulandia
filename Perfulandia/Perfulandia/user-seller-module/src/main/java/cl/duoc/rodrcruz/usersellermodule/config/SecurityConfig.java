package cl.duoc.rodrcruz.usersellermodule.config;
import cl.duoc.rodrcruz.usersellermodule.security.JwtAuthEntryPoint;
import cl.duoc.rodrcruz.usersellermodule.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtAuthEntryPoint unauthorizedHandler;
    private final JwtAuthFilter jwtAuthFilter;

    @Autowired
    @Qualifier("sellerUserDetailsService")
    private UserDetailsService sellerUserDetailsService;

    public SecurityConfig(JwtAuthEntryPoint unauthorizedHandler, JwtAuthFilter jwtAuthFilter) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean // <-- ¡¡¡AÑADE ESTE BEAN!!!
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean // Define el DaoAuthenticationProvider explícito
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(sellerUserDetailsService); // Usa tu UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // <-- ¡¡¡LLAMA AL MÉTODO DEL BEAN DIRECTAMENTE AQUÍ!!!
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // 1. Permitir acceso público a la consola H2 (¡MUY IMPORTANTE EL ORDEN!)
                        // El '**' es crucial para incluir todos los recursos internos de la consola (CSS, JS, etc.)
                        .requestMatchers("/h2-console/**").permitAll()
                        // 2. Permitir acceso público a los endpoints de autenticación y registro
                        .requestMatchers("/api/auth/**").permitAll()
                        // 3. Cualquier otra solicitud DEBE estar autenticada
                        .anyRequest().authenticated()
                );

        // Añade nuestro filtro JWT antes del filtro de autenticación de usuario/contraseña de Spring Security
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Es importante deshabilitar los headers de protección de frames para H2-Console
        // (La consola H2 usa Iframes y esto puede bloquearla)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));


        return http.build();
    }
}
