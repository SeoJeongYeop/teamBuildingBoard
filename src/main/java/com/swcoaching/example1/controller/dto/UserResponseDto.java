package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.user.Role;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String picture;
    private final Role role;
    private final String createdDate;
    private final String modifiedDate;
    private String image;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.picture = entity.getPicture();
        this.role = entity.getRole();
        this.createdDate = applyDateTimePattern(entity.getCreatedDate());
        this.modifiedDate = applyDateTimePattern(entity.getModifiedDate());
        this.image = entity.getImage();
    }

    private String applyDateTimePattern(LocalDateTime t) {
        if (t != null) return t.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return null;
    }

    public void setImage(String imageBase) {
        this.image = imageBase;
    }
}
