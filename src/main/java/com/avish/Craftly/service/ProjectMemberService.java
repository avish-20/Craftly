package com.avish.Craftly.service;

import com.avish.Craftly.dto.member.InviteMemberRequest;
import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.dto.member.UpdateMemberRoleRequest;
import com.avish.Craftly.entity.ProjectMember;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectMemberService {
    List<MemberResponse> getProjectMembers(Long projectId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request);

    MemberResponse updateMemeberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request);

    void removeProjectMember(Long projectId, Long memberId);
}
