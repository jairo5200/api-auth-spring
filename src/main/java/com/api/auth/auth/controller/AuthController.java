package com.api.auth.auth.controller;

import com.api.auth.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
//http://localhost:8080/auth/
@RequiredArgsConstructor
public class AuthController {


    private final AuthService service;


    @GetMapping("/hola")
    public ResponseEntity<Map<String,String>> saludar(){
        Map<String,String> mapa = new HashMap();
        mapa.put("hola","buenas");
        return ResponseEntity.ok(mapa);
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody final RegisterRequest request){
        final TokenResponse token = service.register(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody final LoginRequest request){
        final TokenResponse token = service.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) final String authHeader){
        return service.refreshToken(authHeader);
    }

}
