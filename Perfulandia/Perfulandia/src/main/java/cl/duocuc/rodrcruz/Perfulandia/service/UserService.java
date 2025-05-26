package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.Purchase;
import cl.duocuc.rodrcruz.Perfulandia.model.User;
import cl.duocuc.rodrcruz.Perfulandia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private int nextPurchaseId = 1;

    @Autowired
    private UserRepository userRepository;
    private List<User> users = new ArrayList<>();

    public Optional<User> findByid(int id) {
        return userRepository.getUsers().stream().filter(user -> user.getId() == id).findFirst();
    }


    public void addPurchase(int userid, Purchase purchase) {
        Optional<User> userfind = findByid(userid);
        if (userfind.isPresent()) {
            User user = userfind.get();
            purchase.setId(nextPurchaseId++);
            user.getPurchases().add(purchase);

        }else{
            throw new RuntimeException("User not found");
        }
    }


}
