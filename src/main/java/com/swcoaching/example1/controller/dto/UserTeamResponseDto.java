package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.RelationStatus;
import com.swcoaching.example1.domain.relation.UserTeamRelation;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@RequiredArgsConstructor
public class UserTeamResponseDto {
    private final Long id;
    private final User user;
    private final Team team;
    private final RelationStatus relationStatus;
    private final String createdDate;
    private final String modifiedDate;

    public UserTeamResponseDto(UserTeamRelation entity) {
        this.id = entity.getId();
        this.user = entity.getUser();
        this.team = entity.getTeam();
        this.relationStatus = entity.getRelationStatus();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
