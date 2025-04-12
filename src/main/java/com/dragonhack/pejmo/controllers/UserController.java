package com.dragonhack.pejmo.controllers;

import com.dragonhack.pejmo.dtos.LoginUserDTO;
import com.dragonhack.pejmo.dtos.UserGetDTO;
import com.dragonhack.pejmo.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers() {
        return "samo da dela";
    }

    @GetMapping("/users/{id}")
    public UserGetDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users/me")
    public String getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id) {
        return userService.updateUser(id);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserDTO user) {
        return userService.loginUser();
    }

    @PostMapping("/register")
    public String registerUser() {
        return userService.registerUser();
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}

