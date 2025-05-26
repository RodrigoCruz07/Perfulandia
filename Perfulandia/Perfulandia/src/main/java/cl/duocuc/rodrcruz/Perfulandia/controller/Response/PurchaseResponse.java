package cl.duocuc.rodrcruz.Perfulandia.controller.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PurchaseResponse {
    private int id;
    private int perfumeid;
    private String perfumename;
    private double price;
    private int quantity;
    private Date purchasedate;
    private int userid;
}
