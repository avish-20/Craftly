package com.avish.Craftly.repository;

import com.avish.Craftly.entity.ProjectMember;
import com.avish.Craftly.entity.ProjectMemberId;
import com.avish.Craftly.enums.ProjectRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);

    @Override
    Optional<ProjectMember> findById(ProjectMemberId projectMemberId);


    @Query("SELECT pm.projectRole FROM ProjectMember pm " +
            "where pm.id.projectId = :projectId and pm.id.userId=:userId")
    Optional<ProjectRole> findRoleByProjectIdAndUserId(@Param("projectId")Long projectId, @Param("userId")Long userId);
}
