package com.example.controller;

import com.example.model.User;
import com.example.service.AuthService;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        User user = authService.authenticate(username, password);
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }

        String jwt = Base64.getEncoder().encodeToString((username + ":" + user.getRole()).getBytes());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }
}
