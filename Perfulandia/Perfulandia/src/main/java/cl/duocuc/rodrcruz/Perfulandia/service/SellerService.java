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
    public List<Seller> getAllSellers() {
        return repository.getAllSellers();
    }

    public Seller getSellerById(int id){
        List<Seller> seller = repository.getAllSellers();
        for (Seller s : seller) {
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }

    public Seller addSeller(Seller request){
        List<Seller> seller = repository.getAllSellers();
        int nextId = seller.size()+1;
        request.setId(nextId);
        seller.add(request);
        return request;
    }
    public Seller deleteSeller(int id){
        List<Seller> seller = repository.getAllSellers();
        for( int i = 0; i < seller.size(); i++){
            if(seller.get(i).getId() == id){
                return seller.remove(i);
            }
        }
        return null;
    }
    public Seller updateSeller(Seller request){
        List<Seller> seller = repository.getAllSellers();
        for (int i = 0; i < seller.size(); i++) {
            if (seller.get(i).getId() == request.getId()){
                seller.set(i, request);
                return request;
            }
        }
        return null;
    }

}
