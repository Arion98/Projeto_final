package com.agenda_service_back.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("cliente_senha");
        System.out.println(email);
        System.out.println(senha);

        Map<String, String> authData = authService.authenticate(email, senha);
        if (authData != null) {
            return ResponseEntity.ok(authData);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
