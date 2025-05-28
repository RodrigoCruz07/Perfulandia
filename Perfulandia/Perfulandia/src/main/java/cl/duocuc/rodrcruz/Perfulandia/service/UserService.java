package cl.duocuc.rodrcruz.Perfulandia.service;

import cl.duocuc.rodrcruz.Perfulandia.model.User;
import cl.duocuc.rodrcruz.Perfulandia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.getUsers();
    }
    public Optional<User> findByid(int id) {
        return userRepository.getUsers().stream().filter(user -> user.getId() == id).findFirst();
    }
    public User CreateUser(User user) {
        List<User> users = userRepository.getUsers();

        int newId = users.size() + 1;
        user.setId(newId);
        user.setRegistrationDate(new Date());
        users.add(user);
        return user;
    }




    public User deleteUser(int userid) {
        Optional<User> userfind = findByid(userid);
        if (userfind.isPresent()) {
            User user = userfind.get();
            userRepository.getUsers().remove(user);
            return user;

        }else {
            return null;
    }
    }
    public User updateUser(int userid, User user) {
        Optional<User> userfind = findByid(userid);
        if (userfind.isPresent()) {
            User user1 = userfind.get();
            if(user.getName()!=null){
                user1.setName(user.getName());

            }
            if(user.getEmail()!=null){
                user1.setEmail(user.getEmail());
            }
            if(user.getPhone()!=null){
                user1.setPhone(user.getPhone());
            }
            if(user.getLastname()!=null){
                user1.setLastname(user.getLastname());
            }
            if(user.getAge()!=0){
                user1.setAge(user.getAge());
            }
            return user1;

        }else{
            return null;
        }
    }




}
