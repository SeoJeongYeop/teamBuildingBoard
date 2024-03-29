package com.swcoaching.example1.config.auth.dto;

import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Long Id;
    private final String name;
    private final String email;
    private final String picture;
    private final String image;

    public SessionUser(User user) {
        this.Id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.image = user.getImage();
    }
}
