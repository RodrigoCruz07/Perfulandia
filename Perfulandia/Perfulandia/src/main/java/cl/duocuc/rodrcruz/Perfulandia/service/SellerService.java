package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Seller;
import cl.duocuc.rodrcruz.Perfulandia.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository repository;
    public List<Seller> getRepository() {
        return repository.getAllSellers();
    }

    public Seller getPotitionById(int elementId){
        List<Seller> seller = repository.getAllSellers();
        if (elementId>= 0 && elementId < seller.size() ){
            return seller.get(elementId);
        }
        return null;
    }

    public Seller addSeller(Seller request){
        List<Seller> seller = repository.getAllSellers();
        int nextId = seller.size()+1;
        Seller newSeller = new Seller(nextId,request.getName(),request.getLastname(),request.getAge(),
                request.getAddress(),request.getPhone(),request.getEmail(),request.getRole());
        seller.add(newSeller);
        return newSeller;
    }
    public Seller deleterSeller(int elementId){
        List<Seller> seller = repository.getAllSellers();
        if (getPotitionById(elementId) != null){
            return seller.remove(elementId);
        }
        return null;

    }

}
