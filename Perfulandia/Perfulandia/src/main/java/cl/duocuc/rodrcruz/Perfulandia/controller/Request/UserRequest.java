package cl.duocuc.rodrcruz.Perfulandia.controller.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequest {
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String phone;
}
