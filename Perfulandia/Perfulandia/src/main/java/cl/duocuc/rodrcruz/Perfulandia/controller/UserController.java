package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.model.User;
import cl.duocuc.rodrcruz.Perfulandia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User>users= userService.findAll();
        return ResponseEntity.ok(users);

    }
    @GetMapping("/{elementid}")
    public ResponseEntity<User> getUserById(@PathVariable int elementid) {
        Optional<User> userOptional= userService.findByid(elementid);
        if(userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());

        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User create = userService.CreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);

    }
    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        Optional<User> userOptional= userService.findByid(user.getId());
        if(userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }else{
            return ResponseEntity.notFound().build();

        }
    }

    }



