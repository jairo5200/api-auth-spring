package com.api.auth.auth.controller;

public record LoginRequest(
        String email,
        String password
) {
}
