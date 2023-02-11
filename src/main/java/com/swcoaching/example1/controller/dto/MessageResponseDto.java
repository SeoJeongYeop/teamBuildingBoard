package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.relation.Message;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class MessageResponseDto {

    private final Long id;
    private final String content;
    private final User user;
    private final String createdDate;
    private final String modifiedDate;

    public MessageResponseDto(Message entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.user = entity.getUser();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
