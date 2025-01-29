package com.api.auth.auth.controller;

public record RegisterRequest(
        String email,
        String password,
        String name
) {
}
