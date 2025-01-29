package com.api.auth.auth.service;

import com.api.auth.auth.controller.LoginRequest;
import com.api.auth.auth.controller.RegisterRequest;
import com.api.auth.auth.controller.TokenResponse;
import com.api.auth.auth.repository.Token;
import com.api.auth.auth.repository.TokenRepository;
import com.api.auth.usuario.repository.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;

    public TokenResponse register(RegisterRequest request){
        return null;
    }

    public TokenResponse login(LoginRequest request){
        return null;
    }

    private void saveUserToken(User user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
    }

    public TokenResponse refreshToken(String authHeader){
        return null;
    }
}
