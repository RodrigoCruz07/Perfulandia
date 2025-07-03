package cl.duoc.rodrcruz.usersellermodule.controller.response;
import lombok.AllArgsConstructor; // Para constructor con todos los argumentos
import lombok.Data;
import lombok.NoArgsConstructor; // Para constructor sin argumentos

@Data // Anotación de Lombok
@AllArgsConstructor // Anotación de Lombok
@NoArgsConstructor // Anotación de Lombok
public class AuthResponse {
    private String token;
    private String message;
}
