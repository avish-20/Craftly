package com.avish.Craftly.dto.auth;

public record AuthResponse(String token, UserProfileResponse user) {
}
