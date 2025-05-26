package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.controller.Request.PurchaseRequest;
import cl.duocuc.rodrcruz.Perfulandia.controller.Response.PurchaseResponse;
import cl.duocuc.rodrcruz.Perfulandia.model.Purchase;
import cl.duocuc.rodrcruz.Perfulandia.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @GetMapping
    public ResponseEntity<List<PurchaseResponse>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        List<PurchaseResponse>responses = new ArrayList<>();
        for (Purchase purchase : purchases) {
            responses.add(convertToResponse(purchase));
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @GetMapping("/{elementid}")
    public ResponseEntity<PurchaseResponse> getPurchaseById(@PathVariable int elementid) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(elementid);
        if (purchase.isPresent()) {
            return new ResponseEntity<>(convertToResponse(purchase.get()), HttpStatus.OK);

        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<PurchaseResponse> createPurchase(@RequestBody PurchaseRequest request) {

        Purchase purchase = convertToEntity(request);
        Purchase savedpurchase=purchaseService.addPurchase(purchase);
        if(savedpurchase!=null){
            return new ResponseEntity<>(convertToResponse(savedpurchase), HttpStatus.CREATED);

        }else{
            return ResponseEntity.badRequest().build();

        }
    }
    @DeleteMapping("/{elementid}")

    public ResponseEntity<PurchaseResponse> deletePurchase(@PathVariable int elementid) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(elementid);
        if (purchase.isPresent()) {
            purchaseService.deletePurchaseById(elementid);
            PurchaseResponse purchaseResponse = convertToResponse(purchase.get());
            return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);

        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{purchaseid}/perfume/{newperfumeid}")
    public ResponseEntity<?> updatepurchase(@PathVariable int purchaseid, @PathVariable int newperfumeid) {
        Purchase updatedpurchase = purchaseService.updatePurchase(purchaseid, newperfumeid);
        if(updatedpurchase!=null){
            return new ResponseEntity<>(convertToResponse(updatedpurchase), HttpStatus.OK);

        }else{
            return new ResponseEntity<>("Compra o perfume no encontrado",HttpStatus.NOT_FOUND);
        }
    }






    private PurchaseResponse convertToResponse(Purchase purchase) {
        PurchaseResponse response = new PurchaseResponse();
        response.setId(purchase.getId());
        response.setPerfumeid(purchase.getPerfumeid());
        response.setPerfumename(purchase.getPerfumename());
        response.setPrice(purchase.getPrice());
        response.setQuantity(purchase.getQuantity());
        response.setPurchasedate(purchase.getPurchasedate());
        response.setUserid(purchase.getUserid());
        return response;
    }
    private Purchase convertToEntity(PurchaseRequest request) {
        Purchase purchase = new Purchase();
        purchase.setPerfumeid(request.getPerfumeid());
        purchase.setQuantity(request.getQuantity());
        purchase.setUserid(request.getUserid());
        return purchase;
    }
}
