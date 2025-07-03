package cl.duoc.rodrcruz.usersellermodule.service;
import cl.duoc.rodrcruz.usersellermodule.repository.SellerDB;
import cl.duoc.rodrcruz.usersellermodule.repository.SellerJpaRepository;
import jakarta.annotation.PostConstruct;
// Importa Logger (si usas SLF4J y Logback, que es lo común en Spring Boot)
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service("sellerUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SellerJpaRepository sellerJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SellerDB seller = sellerJpaRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Vendedor no encontrado con email: " + email));
        String retrievedPassword = seller.getPassword();

        String plainTextPasswordForTest = "password";
        BCryptPasswordEncoder tempEncoder = new BCryptPasswordEncoder();
        boolean matchesDirectly = tempEncoder.matches(plainTextPasswordForTest, retrievedPassword);


        GrantedAuthority authority = new SimpleGrantedAuthority(seller.getRole().getName());

        // Retorna un objeto User de Spring Security
        return new User(seller.getEmail(), seller.getPassword(), Collections.singletonList(authority));
    }
}