package com.example.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Base64;

@Component
public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String decoded = new String(Base64.getDecoder().decode(token));
        String[] parts = decoded.split(":");

        if (parts.length != 2) {
            throw new ServletException("Invalid token format");
        }

        request.setAttribute("username", parts[0]);
        request.setAttribute("role", parts[1]);

        chain.doFilter(request, response);
    }
}
