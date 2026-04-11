package com.avish.Craftly.dto.member;

import com.avish.Craftly.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
