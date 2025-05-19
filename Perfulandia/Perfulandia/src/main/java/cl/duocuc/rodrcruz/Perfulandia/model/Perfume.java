package cl.duocuc.rodrcruz.Perfulandia.model;
import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name="perfume")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20,nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer cantity;
    @Column(nullable = false)
    private String brand;

}
