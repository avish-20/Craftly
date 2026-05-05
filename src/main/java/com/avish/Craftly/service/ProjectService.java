package com.avish.Craftly.service;

import com.avish.Craftly.dto.project.ProjectRequest;
import com.avish.Craftly.dto.project.ProjectResponse;
import com.avish.Craftly.dto.project.ProjectSummaryResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);
}
