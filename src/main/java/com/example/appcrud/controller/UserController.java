package com.example.appcrud.controller;

import com.example.appcrud.model.User;
import com.example.appcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity <List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity <User> getUserById(@PathVariable int id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity <User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(this.userService.createUser(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @DeleteMapping("/user/{id}")
    public HttpStatus deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
        return HttpStatus.OK;
    }


}
