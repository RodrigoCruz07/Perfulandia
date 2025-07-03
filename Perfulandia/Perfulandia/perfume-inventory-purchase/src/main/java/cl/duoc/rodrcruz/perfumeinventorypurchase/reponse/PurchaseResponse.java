package cl.duoc.rodrcruz.perfumeinventorypurchase.reponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseResponse {
    private Integer id;
    private String userId;
    private String perfumeName;
    private Integer quantity;
    private Double price;
    private LocalDateTime purchaseDate;
}