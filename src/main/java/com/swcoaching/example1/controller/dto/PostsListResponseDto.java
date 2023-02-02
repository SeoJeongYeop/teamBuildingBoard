package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.board.BoardEntity;
import com.swcoaching.example1.domain.posts.PostsEntity;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime modifiedDate;
    private final BoardEntity board;
    private final User user;


    public PostsListResponseDto(PostsEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.board = entity.getBoard();
        this.user = entity.getUser();
    }
}
