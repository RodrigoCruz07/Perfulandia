package cl.duoc.rodrcruz.perfumeinventorypurchase.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
    private Integer perfumeId;
    private Integer quantity;
    private double price;
    private String location;
}

