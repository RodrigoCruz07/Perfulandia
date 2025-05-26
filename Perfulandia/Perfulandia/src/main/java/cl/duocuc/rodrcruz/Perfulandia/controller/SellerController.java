package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.controller.Request.SellerRequest;
import cl.duocuc.rodrcruz.Perfulandia.controller.Response.SellerResponse;
import cl.duocuc.rodrcruz.Perfulandia.model.Seller;
import cl.duocuc.rodrcruz.Perfulandia.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @GetMapping
    public ResponseEntity<List<SellerResponse>> getSellers() {
        List<Seller> sellers= sellerService.getAllSellers();
        List<SellerResponse> sellerResponses = new ArrayList<>();
        for (Seller seller: sellers) {
            sellerResponses.add(convertToResponse(seller));
        }
        return ResponseEntity.ok(sellerResponses);
    }
    @GetMapping("/{elementId}")
    public ResponseEntity<SellerResponse> getSellers(@PathVariable int elementId) {
        Seller found = sellerService.getSellerById(elementId);
        if (found == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(found));
    }

    @PutMapping("/{elementId}")
    public ResponseEntity<SellerResponse> updateSeller(@PathVariable int elementId, @RequestBody SellerRequest request) {
        Seller seller=convertToEntity(request);
        seller.setId(elementId);
        Seller updateSeller = sellerService.updateSeller(seller);
        if (updateSeller == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(updateSeller));
    }
    @PostMapping
    public ResponseEntity<SellerResponse> createSeller(@RequestBody SellerRequest request) {
        Seller seller= convertToEntity(request);
        Seller found = sellerService.addSeller(seller);
        return new ResponseEntity<>(convertToResponse(found), HttpStatus.CREATED);
    }


    @DeleteMapping("/{elementId}")
    public ResponseEntity<SellerResponse> deleteSeller(@PathVariable int elementId){
        Seller deleteSeller = sellerService.deleteSeller(elementId);
        if (deleteSeller == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResponse(deleteSeller));
    }
    private Seller convertToEntity(SellerRequest request) {
        Seller seller = new Seller();
        seller.setName(request.getName());
        seller.setLastname(request.getLastname());
        seller.setAge(request.getAge());
        seller.setAddress(request.getAddress());
        seller.setPhone(request.getPhone());
        seller.setEmail(request.getEmail());
        seller.setRole(request.getRole());
        return seller;
    }
    private SellerResponse convertToResponse(Seller seller) {
        SellerResponse response = new SellerResponse();
        response.setId(seller.getId());
        response.setName(seller.getName());
        response.setLastname(seller.getLastname());
        response.setAge(seller.getAge());
        response.setAddress(seller.getAddress());
        response.setPhone(seller.getPhone());
        response.setEmail(seller.getEmail());
        response.setRole(seller.getRole());
        return response;
    }

}
