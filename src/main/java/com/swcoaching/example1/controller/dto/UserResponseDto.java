package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.user.Role;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String picture;
    private final Role role;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
    }
}
