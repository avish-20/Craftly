package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.project.ProjectRequest;
import com.avish.Craftly.dto.project.ProjectResponse;
import com.avish.Craftly.dto.project.ProjectSummaryResponse;
import com.avish.Craftly.entity.Project;
import com.avish.Craftly.entity.ProjectMember;
import com.avish.Craftly.entity.ProjectMemberId;
import com.avish.Craftly.entity.User;
import com.avish.Craftly.enums.ProjectRole;
import com.avish.Craftly.error.ResourceNotFoundException;
import com.avish.Craftly.mapper.ProjectMapper;
import com.avish.Craftly.repository.ProjectMemberRepository;
import com.avish.Craftly.repository.ProjectRepository;
import com.avish.Craftly.repository.UserRepository;
import com.avish.Craftly.security.AuthUtil;
import com.avish.Craftly.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    AuthUtil authUtil;

    @Override
    public List<ProjectSummaryResponse> getUserProjects() {
//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());
        Long userId = authUtil.getCurrentUserId();
        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);


    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        //User owner = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", userId.toString()));
        //For above line we are making db call but we can create proxy reference object with
        // id just from jpa It will not make  adb call unless other filed s of owner required"
        //Make sure to use this with transactional annotation, otherwise it will throw error when we try to save project member because of detached entity
        User owner = userRepository.getReferenceById(userId);
        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();
        project = projectRepository.save(project);
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);


        //model mapper does not fully support record


        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
//        if(!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to update the name");
//        }
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = authUtil.getCurrentUserId();
        Project project = getAccessibleProjectById(id, userId);
//        if(!project.getOwner().getId().equals(userId)) {
//            throw new RuntimeException("You are not allowed to delete the project");
//        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    //Internal methods
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleById(id, userId).orElseThrow(
                () -> new ResourceNotFoundException("Project", id.toString())
        );
    }
}
