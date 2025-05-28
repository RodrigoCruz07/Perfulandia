package cl.duocuc.rodrcruz.Perfulandia.model;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;

    private String name;

    private String lastname;

    private int age;

    private String email;

    private String phone;

    private Date registrationDate;


}
