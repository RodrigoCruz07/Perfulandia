package cl.duoc.rodrcruz.perfumeinventorypurchase.controller.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class PurchaseRequest {
    private int userid;
    private String perfumeid;
    private Integer quantity;
    private double price;
}
