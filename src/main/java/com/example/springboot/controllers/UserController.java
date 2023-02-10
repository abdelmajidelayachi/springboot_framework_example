package com.example.springboot.controllers;

import com.example.springboot.payload.requests.UserRequest;
import com.example.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        return ResponseEntity.ok().body(Map.of("status", "success", "data", userService.findAllUser()));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok().body(Map.of("status", "success", "data", userService.saveUser(userRequest)));
    }

}
