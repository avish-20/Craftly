package com.avish.Craftly.service;

import com.avish.Craftly.dto.member.InviteMemberRequest;
import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.entity.ProjectMember;
import org.jspecify.annotations.Nullable;

public interface ProjectMemberService {
    ProjectMember getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemeberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId);

    MemberResponse deleteMemeberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId);
}
