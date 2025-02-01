package com.api.auth.auth.service;

import com.api.auth.auth.controller.LoginRequest;
import com.api.auth.auth.controller.RegisterRequest;
import com.api.auth.auth.controller.TokenResponse;
import com.api.auth.auth.repository.Token;
import com.api.auth.auth.repository.TokenRepository;
import com.api.auth.usuario.repository.User;
import com.api.auth.usuario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public TokenResponse register(RegisterRequest request){
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser,jwtToken);
        return new TokenResponse(jwtToken,refreshToken);
    }

    public TokenResponse login(LoginRequest request){
        return null;
    }

    private void saveUserToken(User user, String jwtToken){
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    public TokenResponse refreshToken(final String authHeader){
        return null;
    }
}
