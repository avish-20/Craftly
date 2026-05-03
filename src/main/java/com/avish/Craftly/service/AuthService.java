package com.avish.Craftly.service;

import com.avish.Craftly.dto.auth.AuthResponse;
import com.avish.Craftly.dto.auth.LoginRequest;
import com.avish.Craftly.dto.auth.SignupRequest;


public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
