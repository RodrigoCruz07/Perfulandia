package cl.duocuc.rodrcruz.Perfulandia.repository;

import cl.duocuc.rodrcruz.Perfulandia.model.Purchase;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Repository
public class PurchaseRepository {
    private  final List<Purchase> purchases= new ArrayList<>();
    public PurchaseRepository() {
        purchases.add(new Purchase(1, 1, 1, "Sauvage", 2, 85.99, new Date()));
        purchases.add(new Purchase(2, 2, 10, "Invictus", 1, 84.20, new Date()));
        purchases.add(new Purchase(3, 3, 5, "Blue Label", 1, 65.75, new Date()));
        purchases.add(new Purchase(4, 4, 3, "Humor", 3, 39.99, new Date()));
        purchases.add(new Purchase(5, 5, 2, "One Million", 1, 78.50, new Date()));
        purchases.add(new Purchase(6, 1, 7, "Polo Red", 1, 72.30, new Date()));
        purchases.add(new Purchase(7, 2, 4, "212 VIP Black", 2, 90.00, new Date()));
    }
    public List<Purchase> getPurchases() {
        return purchases;
    }
}
