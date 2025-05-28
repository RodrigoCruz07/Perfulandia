package cl.duocuc.rodrcruz.Perfulandia.controller.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRequest {
    private String name;
    private String lastname;
    private int age;
    private String address;
    private String phone;
    private String email;
    private String role;

}
