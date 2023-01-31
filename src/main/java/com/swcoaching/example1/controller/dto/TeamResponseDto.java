package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.team.Team;
import lombok.Getter;

@Getter
public class TeamResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String picture;

    public TeamResponseDto(Team entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.picture = entity.getPicture();
    }
}
