package cl.duocuc.rodrcruz.Perfulandia.controller;

import cl.duocuc.rodrcruz.Perfulandia.controller.Request.UserRequest;
import cl.duocuc.rodrcruz.Perfulandia.controller.Response.UserResponse;
import cl.duocuc.rodrcruz.Perfulandia.model.User;
import cl.duocuc.rodrcruz.Perfulandia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<User>users= userService.findAll();
        List<UserResponse> userResponses=new ArrayList<>();
        for (User user : users) {
            userResponses.add(convertToResponse(user));

        }
        return ResponseEntity.ok(userResponses);


    }
    @GetMapping("/{elementid}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int elementid) {
        Optional<User> userOptional= userService.findByid(elementid);
        if(userOptional.isPresent()) {
            return ResponseEntity.ok(convertToResponse(userOptional.get()));

        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        User newuser=convertToEntity(request);
        User created = userService.CreateUser(newuser);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponse(created));

    }
    @DeleteMapping("/{elementid}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable int elementid ) {
        Optional<User> userOptional= userService.findByid(elementid);
        if(userOptional.isPresent()) {
            userService.deleteUser(elementid);
            return ResponseEntity.ok(convertToResponse(userOptional.get()));
        }else{
            return ResponseEntity.notFound().build();

        }
    }
    @PutMapping("/{elementid}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int elementid ,@RequestBody UserRequest request) {
       User found = userService.updateUser(elementid,convertToEntity(request));
        if (found != null) {
            return ResponseEntity.ok(convertToResponse(found));
        }
       return ResponseEntity.notFound().build();
    }
    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastname(user.getLastname());
        response.setAge(user.getAge());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRegistrationDate(user.getRegistrationDate());
        return response;
    }

    private User convertToEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setAge(request.getAge());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        return user;
    }

    }



