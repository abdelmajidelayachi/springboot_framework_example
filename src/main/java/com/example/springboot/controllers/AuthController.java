package com.example.springboot.controllers;

import com.example.springboot.payload.responses.AuthResponse;
import com.example.springboot.payload.requests.auth.LoginRequest;
import com.example.springboot.payload.requests.auth.RegisterRequest;
import com.example.springboot.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest registerRequest){
        return ResponseEntity.ok(authService.login(registerRequest));
    }

}
