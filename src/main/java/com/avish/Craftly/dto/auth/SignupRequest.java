package com.avish.Craftly.dto.auth;

public record SignupRequest(
        String emaail,
        String name,
        String password
) {
}
