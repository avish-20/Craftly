package com.avish.Craftly.controller;

import com.avish.Craftly.dto.auth.AuthResponse;
import com.avish.Craftly.dto.auth.LoginRequest;
import com.avish.Craftly.dto.auth.SignupRequest;
import com.avish.Craftly.dto.auth.UserProfileResponse;
import com.avish.Craftly.service.AuthService;
import com.avish.Craftly.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        // Implement login logic here
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getProfile() {
        Long userId = 1L;
        return ResponseEntity.ok(userService.getProfile(userId));
    }

}
