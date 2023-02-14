package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.Status;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class TeamResponseDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String picture;
    private final Status status;
    private final User owner;
    private final String createdDate;
    private final String modifiedDate;
    private boolean isOwner;
    private final String image;

    public TeamResponseDto(Team entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.picture = entity.getPicture();
        this.status = entity.getStatus();
        this.owner = entity.getOwner();
        this.createdDate = applyDateTimePattern(entity.getCreatedDate());
        this.modifiedDate = applyDateTimePattern(entity.getModifiedDate());
        this.isOwner = false;
        this.image = entity.getImage();
    }

    public boolean isOwner() {
        return this.isOwner;
    }

    private String applyDateTimePattern(LocalDateTime t) {
        if (t != null) return t.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return null;
    }

    public void checkOwner(Long userId) {
        User user1 = this.getOwner();
        if (user1 != null && user1.getId().equals(userId)) {
            this.isOwner = true;
        }
    }
}
