package cl.duoc.rodrcruz.usersellermodule.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name="sellers")
public class SellerDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name",updatable = true, nullable = false)
    private String name;
    @Column(name = "lastname",updatable = true, nullable = false)
    private String lastname;
    @Column(name = "age",updatable = true, nullable = false)
    private Integer age;
    @Column(name = "address",updatable = true, nullable = false)
    private String address;
    @Column(name = "phone",updatable = true, nullable = false)
    private String phone;
    @Column(name = "email",updatable = true, nullable = false)
    private String email;
    @Column(name = "role",updatable = true, nullable = false)
    private String role;


}
