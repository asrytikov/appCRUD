package com.example.appcrud.controller;

import com.example.appcrud.model.User;
import com.example.appcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView getAllUsers(){
        Iterable<User> users = userService.getAllUsers();
        return new ModelAndView("index", "usersList", users);
    }

    @GetMapping("/user/{id}")
    public ModelAndView getUserById(@PathVariable int id){
        User user = userService.getUserById(id);
        return new ModelAndView("user", "user", user);
    }

    @GetMapping("create")
    public ModelAndView createUserForm(@ModelAttribute User user){
        return new ModelAndView("create", "user", user);
    }

    @PostMapping
    public ModelAndView createUser(@ModelAttribute User user){
        user = this.userService.createUser(user);
        return new ModelAndView("redirect:/user/{user.id}", "user.id", user.getId());
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateUser(@PathVariable int id){
        User user = userService.getUserById(id);
        return new ModelAndView("update", "user", user);
    }

    @PostMapping("/updateUser")
    public ModelAndView updateUser(@ModelAttribute User user){
        this.userService.updateUser(user);
        Iterable<User> users = userService.getAllUsers();
        return new ModelAndView("redirect:/", "usersList", users);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
        Iterable<User> users = userService.getAllUsers();
        return new ModelAndView("redirect:/", "usersList", users);
    }


}
