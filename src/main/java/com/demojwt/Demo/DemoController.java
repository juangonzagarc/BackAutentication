package com.demojwt.Demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    @GetMapping("/user1/data")
    @PreAuthorize("hasAuthority('USER')")
    public String user1Endpoint() {
        return "Este es el endpoint exclusivo para USER";
    }

    @GetMapping("/user2/data")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String user2Endpoint() {
        return "Este es el endpoint exclusivo para ADMIN";
    }

    @GetMapping("/shared")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public String sharedEndpoint() {
        return "Este endpoint es accesible para USER y ADMIN";
    }
}
