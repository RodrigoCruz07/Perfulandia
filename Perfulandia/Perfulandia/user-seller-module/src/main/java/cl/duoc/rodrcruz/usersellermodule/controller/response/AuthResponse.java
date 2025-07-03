package cl.duoc.rodrcruz.usersellermodule.controller.response;
import lombok.*;

@Getter
@Setter
@Data // Anotación de Lombok
@AllArgsConstructor // Anotación de Lombok
@NoArgsConstructor // Anotación de Lombok
public class AuthResponse {
    private String token;
    private String message;
}
