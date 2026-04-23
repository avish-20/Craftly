package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.member.InviteMemberRequest;
import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.dto.member.UpdateMemberRoleRequest;
import com.avish.Craftly.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemeberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteMemeberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId) {
        return null;
    }
}
