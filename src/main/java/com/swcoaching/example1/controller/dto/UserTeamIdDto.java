package com.swcoaching.example1.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserTeamIdDto {
    private long id;

    public UserTeamIdDto(long id) {
        this.id = id;
    }
}
