package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthService {
    private final List<User> users = List.of(
            new User("userA", "passwordA", "ROLE_A"),
            new User("userB", "passwordB", "ROLE_B")
    );

    public User authenticate(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}
