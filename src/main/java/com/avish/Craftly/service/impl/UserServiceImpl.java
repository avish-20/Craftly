package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.auth.UserProfileResponse;
import com.avish.Craftly.error.ResourceNotFoundException;
import com.avish.Craftly.repository.UserRepository;
import com.avish.Craftly.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    UserRepository userRepository;
    @Override
    public UserProfileResponse getProfile(Long userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with user", username));
    }
}
