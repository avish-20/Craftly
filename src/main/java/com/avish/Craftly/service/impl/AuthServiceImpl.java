package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.auth.AuthResponse;
import com.avish.Craftly.dto.auth.LoginRequest;
import com.avish.Craftly.dto.auth.SignupRequest;
import com.avish.Craftly.entity.User;
import com.avish.Craftly.error.BadRequestException;
import com.avish.Craftly.mapper.UserMapper;
import com.avish.Craftly.repository.UserRepository;
import com.avish.Craftly.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @Override
    public AuthResponse signup(SignupRequest request) {
        userRepository.findByUsername(request.username()).ifPresent(user -> {
            throw new BadRequestException("User already exists with username: "+request.username());
        });
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user = userRepository.save(user);

        return new AuthResponse("dummy", userMapper.toUserProfileResponse(user));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
