package cl.duoc.rodrcruz.perfumeinventorypurchase.controller.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PurchaseRequest {
    private String userid;
    private String perfumeid;
    private Integer quantity;
    private double price;
}
