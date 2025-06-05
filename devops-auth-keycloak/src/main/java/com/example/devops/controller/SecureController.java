package com.example.devops.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class SecureController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is a public API";
    }

    @GetMapping("/secure")
    public String secureApi(Principal principal) {
        return "Hello, " + principal.getName() + "! This is a protected API";
    }
}

