package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TeamReqListResDto {
    private final Long id;
    private final String content;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final Team team;
    private final User user;
    private final Long relationId;

    public TeamReqListResDto(MessageResponseDto msg, UserTeamResponseDto relation) {
        this.id = msg.getId();
        this.content = msg.getContent();
        this.createdDate = msg.getCreatedDate();
        this.modifiedDate = msg.getModifiedDate();
        this.user = msg.getUser();
        this.team = relation.getTeam();
        this.relationId = relation.getId();
    }
}
