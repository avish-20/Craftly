package com.avish.Craftly.service;

import com.avish.Craftly.dto.auth.UserProfileResponse;

public interface  UserService {
    UserProfileResponse getProfile(Long userId);
}
