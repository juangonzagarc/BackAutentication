package com.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @GetMapping("/userA")
    public Map<String, String> userAEndpoint(HttpServletRequest request) {
        verifyRole(request, "ROLE_A");
        return Map.of("message", "Hello User A");
    }

    @GetMapping("/userB")
    public Map<String, String> userBEndpoint(HttpServletRequest request) {
        verifyRole(request, "ROLE_B");
        return Map.of("message", "Hello User B");
    }

    @GetMapping("/shared")
    public Map<String, String> sharedEndpoint(HttpServletRequest request) {
        return Map.of("message", "Hello both users");
    }

    private void verifyRole(HttpServletRequest request, String requiredRole) {
        String role = (String) request.getAttribute("role");
        if (!requiredRole.equals(role)) {
            throw new RuntimeException("Access Denied");
        }
    }
}
