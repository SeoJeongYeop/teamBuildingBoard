package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.RelationStatus;
import com.swcoaching.example1.domain.relation.UserTeamRelation;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
        this.createdDate = applyDateTimePattern(entity.getCreatedDate());
        this.modifiedDate = applyDateTimePattern(entity.getModifiedDate());
    }

    private String applyDateTimePattern(LocalDateTime t) {
        if (t != null) return t.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return null;
    }
}
