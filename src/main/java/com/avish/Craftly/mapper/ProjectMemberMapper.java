package com.avish.Craftly.mapper;

import com.avish.Craftly.dto.member.MemberResponse;
import com.avish.Craftly.entity.ProjectMember;
import com.avish.Craftly.entity.ProjectMemberId;
import com.avish.Craftly.entity.User;
import com.avish.Craftly.enums.ProjectRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);


}
