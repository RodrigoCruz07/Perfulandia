package cl.duocuc.rodrcruz.Perfulandia.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    private int id;
    private String product;
    private int quantity;
}
