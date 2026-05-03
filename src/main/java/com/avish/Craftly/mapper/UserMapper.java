package com.avish.Craftly.mapper;

import com.avish.Craftly.dto.auth.SignupRequest;
import com.avish.Craftly.dto.auth.UserProfileResponse;
import com.avish.Craftly.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(SignupRequest signupRequest);
    UserProfileResponse toUserProfileResponse(User user);
}
