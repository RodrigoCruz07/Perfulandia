package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.model.Seller;
import cl.duocuc.rodrcruz.Perfulandia.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/{ElementId}")
    public ResponseEntity<Seller> getSellers(@PathVariable int ElementId) {
        Seller found = sellerService.getPotitionById(ElementId);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }
    @PutMapping("/{ElementId}")
    public ResponseEntity<Seller> PutSellers(@PathVariable int ElementId, @RequestBody Seller request) {
        Seller found = sellerService.getPotitionById(ElementId);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(found);
    }
    @PostMapping
    public ResponseEntity<Seller> PostSellers(@RequestBody Seller request) {
        Seller found = sellerService.addSeller(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(found);
    }
    @DeleteMapping("/{ElementId}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable int ElementId){
        Seller delete = sellerService.getPotitionById(ElementId);
        if (delete== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(delete);
    }


}
