package cl.duoc.rodrcruz.usersellermodule.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // Importar para @PreAuthorize
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test") // Todas las rutas en este controlador comenzarán con /api/test
public class TestController {

    // Este endpoint requiere autenticación (cualquier usuario logueado puede acceder)
    @GetMapping("/hello")
    public ResponseEntity<String> helloProtected() {
        return ResponseEntity.ok("¡Hola desde un endpoint protegido! Acceso concedido.");
    }

    // Este endpoint requiere el rol 'ADMINISTRADOR'
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRADOR')") // ¡Importante: usa el prefijo 'ROLE_' si tus roles lo tienen en UserDetails!
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("¡Hola, Administrador! Acceso solo para ADMIN.");
    }

    // Este endpoint requiere el rol 'VENDEDOR'
    @GetMapping("/seller")
    @PreAuthorize("hasRole('VENDEDOR')") // ¡Importante: usa el prefijo 'ROLE_' si tus roles lo tienen en UserDetails!
    public ResponseEntity<String> sellerOnly() {
        return ResponseEntity.ok("¡Hola, Vendedor! Acceso solo para VENDEDOR.");
    }
}
