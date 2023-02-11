package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.board.BoardEntity;
import com.swcoaching.example1.domain.posts.PostsEntity;
import com.swcoaching.example1.domain.user.User;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final BoardEntity board;
    private final User user;
    private final String createdDate;
    private final String modifiedDate;
    private boolean isAuthor;


    public PostsResponseDto(PostsEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.board = entity.getBoard();
        this.user = entity.getUser();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.isAuthor = false;
    }

    public boolean isAuthor() {
        return this.isAuthor;
    }

    public void checkAuthor(Long userId) {
        User user1 = this.getUser();
        if (user1 != null && user1.getId().equals(userId)) {
            this.isAuthor = true;
        }
    }
}
