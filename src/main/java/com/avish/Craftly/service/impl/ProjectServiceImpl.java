package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.project.ProjectRequest;
import com.avish.Craftly.dto.project.ProjectResponse;
import com.avish.Craftly.dto.project.ProjectSummaryResponse;
import com.avish.Craftly.entity.Project;
import com.avish.Craftly.entity.User;
import com.avish.Craftly.mapper.ProjectMapper;
import com.avish.Craftly.repository.ProjectRepository;
import com.avish.Craftly.repository.UserRepository;
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

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());

        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);


    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = projectRepository.findAccessibleById(id, userId).orElseThrow();
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .isPublic(false)
                .build();

        project = projectRepository.save(project);
        //model mapper does not fully support record


        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update the name");
        }
        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if(!project.getOwner().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete the project");
        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);

    }

    //Internal methods
    public Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleById(id, userId).orElseThrow();
    }
}
