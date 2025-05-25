package cl.duocuc.rodrcruz.Perfulandia.model;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {

    private int id;
    private String name;
    private int quantity;
    private String brand;

}
