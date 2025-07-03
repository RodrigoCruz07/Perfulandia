package cl.duoc.rodrcruz.perfumeinventorypurchase.Service;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PurchaseDB;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PurchaseJpaRepository;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PerfumeJpaRepository;
import cl.duoc.rodrcruz.perfumeinventorypurchase.repository.PerfumeDB;
import cl.duoc.rodrcruz.perfumeinventorypurchase.controller.request.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseJpaRepository purchaseJpaRepository;

    @Autowired
    private PerfumeJpaRepository perfumeJpaRepository;

    public PurchaseDB registerPurchase(PurchaseRequest request) {
        PerfumeDB perfume = perfumeJpaRepository.findById(Integer.valueOf(request.getPerfumeid()))
                .orElseThrow(() -> new RuntimeException("Perfume no encontrado con ID: " + request.getPerfumeid()));

        PurchaseDB purchase = new PurchaseDB();
        purchase.setUserid(String.valueOf(request.getUserid()));
        purchase.setPerfume(perfume);
        purchase.setPerfumename(perfume.getName());
        purchase.setQuantity(request.getQuantity());
        purchase.setPrice(request.getPrice());
        purchase.setPurchasedate(LocalDateTime.now());

        return purchaseJpaRepository.save(purchase);
    }

    public PurchaseDB getPurchaseById(Integer id) {
        return purchaseJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
    }

    public List<PurchaseDB> findAllPurchases() {
        return purchaseJpaRepository.findAll();
    }

    public boolean deletePurchase(Integer id) {
        PurchaseDB purchase = purchaseJpaRepository.findById(id).orElse(null);
        if (purchase != null) {
            purchaseJpaRepository.delete(purchase);
            return true;
        }
        return false;
    }
}
