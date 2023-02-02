package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.Status;
import com.swcoaching.example1.domain.team.Team;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TeamResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String picture;
    private final Status status;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public TeamResponseDto(Team entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.picture = entity.getPicture();
        this.status = entity.getStatus();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
