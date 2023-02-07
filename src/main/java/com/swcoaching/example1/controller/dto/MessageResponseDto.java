package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.Message;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponseDto {

    private final Long id;
    private final String content;
    private final User user;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public MessageResponseDto(Message entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = entity.getUser();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
