package com.avish.Craftly.mapper;

import com.avish.Craftly.dto.project.ProjectResponse;
import com.avish.Craftly.dto.project.ProjectSummaryResponse;
import com.avish.Craftly.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);
}
