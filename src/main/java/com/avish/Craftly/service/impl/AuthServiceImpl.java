package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.auth.AuthResponse;
import com.avish.Craftly.dto.auth.LoginRequest;
import com.avish.Craftly.dto.auth.SignupRequest;
import com.avish.Craftly.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public AuthResponse signup(SignupRequest request) {
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
