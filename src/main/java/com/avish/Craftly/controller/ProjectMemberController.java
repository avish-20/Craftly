package com.avish.Craftly.controller;

import com.avish.Craftly.dto.member.InviteMemberRequest;
import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.dto.member.UpdateMemberRoleRequest;
import com.avish.Craftly.entity.ProjectMember;
import com.avish.Craftly.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;


    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));


    }

    @PostMapping
    public ResponseEntity<MemberResponse> addProjectMember(@PathVariable Long projectId, @RequestBody InviteMemberRequest request) {
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(projectMemberService.inviteMember(projectId, request, userId));

    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateProjectMemberRole(@PathVariable Long projectId, @PathVariable Long memberId, @RequestBody UpdateMemberRoleRequest request) {
        Long userId = 1L;
        return ResponseEntity.ok(projectMemberService.updateMemeberRole(projectId, memberId, request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeProjectMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        Long userId = 1L;
        projectMemberService.removeProjectMember(projectId, memberId, userId);
        return ResponseEntity.noContent().build();
    }

}
