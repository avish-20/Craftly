package com.avish.Craftly.dto.auth;

public record UserProfileResponse(
        Long id,
        String username,
        String name
        ) {
}
