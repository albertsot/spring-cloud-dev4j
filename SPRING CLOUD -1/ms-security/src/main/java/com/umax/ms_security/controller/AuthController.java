package com.umax.ms_security.controller;

import com.umax.ms_security.model.dto.TokenDTO;
import com.umax.ms_security.model.dto.UserDTO;
import com.umax.ms_security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authservice;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(authservice.login(userDTO));
    }
    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(authservice.save(userDTO));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validateToken(@RequestParam String token ){
        return ResponseEntity.ok(authservice.validate(token));
    }
}
