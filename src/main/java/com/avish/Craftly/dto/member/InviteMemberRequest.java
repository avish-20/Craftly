package com.avish.Craftly.dto.member;

import com.avish.Craftly.enums.ProjectRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record InviteMemberRequest(
        @Email @NotNull String username,
        @NotNull ProjectRole role
        ) {
}
