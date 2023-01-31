package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamSaveRequestDto {
    private String name;
    private String description;
    private String picture;

    @Builder
    public TeamSaveRequestDto(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    public Team toEntity() {
        return Team.builder()
                .name(name)
                .description(description)
                .picture(picture)
                .build();
    }
}
