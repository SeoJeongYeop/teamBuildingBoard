package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.posts.PostsEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private long boardId;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author, long boardId) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.boardId = boardId;
    }

    public PostsEntity toEntity() {
        return PostsEntity.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
