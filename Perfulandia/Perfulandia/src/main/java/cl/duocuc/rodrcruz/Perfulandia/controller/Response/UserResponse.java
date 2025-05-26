package cl.duocuc.rodrcruz.Perfulandia.controller.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class UserResponse {
    private int id;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String phone;
    private Date registrationDate;
}
