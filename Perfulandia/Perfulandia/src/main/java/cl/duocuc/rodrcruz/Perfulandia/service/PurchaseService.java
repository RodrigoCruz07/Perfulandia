package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Perfume;
import cl.duocuc.rodrcruz.Perfulandia.model.Purchase;
import cl.duocuc.rodrcruz.Perfulandia.repository.PerfumeRepository;
import cl.duocuc.rodrcruz.Perfulandia.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    private int nextPurchaseId = 8;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PerfumeRepository perfumeRepository;
    @Autowired
    private PerfumeService perfumeService;

    public Purchase addPurchase(Purchase purchase) {
        Optional<Perfume> perfume = perfumeService.findById(purchase.getPerfumeid());
        if (perfume.isPresent()) {
            Perfume perfume1 = perfume.get();
            if (perfume1.getQuantity() >= purchase.getQuantity()) {
                perfume1.setQuantity(perfume1.getQuantity() - purchase.getQuantity());
                purchase.setId(nextPurchaseId++);
                purchase.setPerfumename(perfume1.getName());
                purchase.setPrice(perfume1.getPrice());
                purchase.setPurchasedate(new Date());
                purchaseRepository.getPurchases().add(purchase);
                return purchase;

            } else {
                return null;
            }

        }
        return null;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.getPurchases();

    }

    public Optional<Purchase> getPurchaseById(int id) {
        return purchaseRepository.getPurchases().stream().filter(p -> p.getId() == id).findFirst();
    }

    public boolean deletePurchaseById(int id) {
        Optional<Purchase> purchase = getPurchaseById(id);
        if (purchase.isPresent()) {
            return purchaseRepository.getPurchases().remove(purchase.get());

        } else {
            return false;
        }
    }
    public Purchase updatePurchase(int purchaseid,int newPerfumeid) {


        Optional<Purchase> purchase = getPurchaseById(purchaseid);
        Optional<Perfume> perfumeOptional =perfumeService.findById(newPerfumeid);

        if (purchase.isPresent() && perfumeOptional.isPresent()) {
            Purchase existingPurchase = purchase.get();
            Perfume newPerfume = perfumeOptional.get();

            //Aqui buscamos el perfume original de la compra existente
            Optional<Perfume> oldPerfumeOpt =perfumeService.findById(existingPurchase.getPerfumeid());
            //verificamos si la cantidad del nuevo perfume es menor que la cantidad de la compra
            if (newPerfume.getQuantity() < existingPurchase.getQuantity()) {
                return null;
            }
            //aqui estariamos devolviendo el stock que se habia restado anteriormente
            if (oldPerfumeOpt.isPresent()){
                Perfume oldPerfume = oldPerfumeOpt.get();
                oldPerfume.setQuantity(oldPerfume.getQuantity() + existingPurchase.getQuantity());
            }

            //aqui estariamos descontando lo del nuevo perfume
            newPerfume.setQuantity(newPerfume.getQuantity() - existingPurchase.getQuantity());

            //aqui actualizariamos los datos de la compra
            existingPurchase.setPerfumeid(newPerfume.getId());
            existingPurchase.setPerfumename(newPerfume.getName());
            existingPurchase.setPrice(newPerfume.getPrice());
            return existingPurchase;
        }
        return null;

    }

}
