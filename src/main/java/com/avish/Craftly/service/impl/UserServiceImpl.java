package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.auth.UserProfileResponse;
import com.avish.Craftly.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }
}
