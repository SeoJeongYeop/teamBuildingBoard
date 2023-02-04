package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.user.Role;
import com.swcoaching.example1.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String picture;
    private Role role;

    @Builder
    public UserSaveRequestDto(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(role)
                .build();
    }

}
