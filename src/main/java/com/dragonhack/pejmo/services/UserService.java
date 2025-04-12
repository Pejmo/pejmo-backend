package com.dragonhack.pejmo.services;

import com.dragonhack.pejmo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserById(Long id) {
        return "getUserById";
    }

    public String getCurrentUser() {
        return "getCurrentUser";
    }

    public String updateUser(Long id) {
        return "updateUser";
    }

    public String loginUser() {
        return "loginUser";
    }

    public String registerUser() {
        return "registerUser";
    }

    public String deleteUser(Long id) {
        return "deleteUser";
    }
}
