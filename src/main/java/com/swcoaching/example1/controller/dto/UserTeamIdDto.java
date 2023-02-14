package com.swcoaching.example1.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserTeamIdDto {
    private Long id;

    public UserTeamIdDto(Long id) {
        this.id = id;
    }
}
