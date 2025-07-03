package cl.duoc.rodrcruz.usersellermodule.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String lastname;
    private Integer age;
    private String address;
    private String phone;
    private String email;
    private String roleName;
    private String password;

}
