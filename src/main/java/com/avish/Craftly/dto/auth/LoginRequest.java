package com.avish.Craftly.dto.auth;

public record LoginRequest(
        String email,
        String password
) {
}
