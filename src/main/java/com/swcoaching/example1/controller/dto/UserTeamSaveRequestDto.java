package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.RelationStatus;
import com.swcoaching.example1.domain.relation.UserTeamRelation;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserTeamSaveRequestDto {
    private long userId;
    private long teamId;
    private RelationStatus relationStatus;
    private String content;

    public UserTeamSaveRequestDto(long userId, long teamId, RelationStatus status, String content) {
        this.userId = userId;
        this.teamId = teamId;
        this.relationStatus = status;
        this.content = content;
    }

    public UserTeamRelation toEntity() {
        return UserTeamRelation.builder()
                .relationStatus(relationStatus)
                .build();
    }
}
