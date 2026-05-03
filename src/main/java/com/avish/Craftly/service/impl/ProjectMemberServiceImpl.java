package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.member.InviteMemberRequest;
import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.dto.member.UpdateMemberRoleRequest;
import com.avish.Craftly.entity.Project;
import com.avish.Craftly.entity.ProjectMember;
import com.avish.Craftly.entity.ProjectMemberId;
import com.avish.Craftly.entity.User;
import com.avish.Craftly.mapper.ProjectMemberMapper;
import com.avish.Craftly.repository.ProjectMemberRepository;
import com.avish.Craftly.repository.ProjectRepository;
import com.avish.Craftly.repository.UserRepository;
import com.avish.Craftly.service.ProjectMemberService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Transactional
public class ProjectMemberServiceImpl implements ProjectMemberService {
    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    private final UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        List<MemberResponse> memberResponseList = new ArrayList<>();
        //memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .forEach(memberResponseList::add);
        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not allowed");
//        }
        User invitee = userRepository.findByUsername(request.username()).orElseThrow();
        if (invitee.getId().equals(userId)) {
            throw new RuntimeException("You cannot invite yourself");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if (projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("User is already a member");
        }
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);

    }

    @Override
    public MemberResponse updateMemeberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not allowed");
//        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        System.out.println(projectMember);
        if (projectMember.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot update your own role");
        }
        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
//        if (!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("Not allowed");
//        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if(!projectMemberRepository.existsById(projectMemberId)) {
            throw new RuntimeException("User is not a member");
        }
        projectMemberRepository.deleteById(projectMemberId);
        //return projectMemberMapper.toProjectMemberResponseFromMember(projectMember);
    }

    //Internal methods
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleById(id, userId).orElseThrow();
    }
}
