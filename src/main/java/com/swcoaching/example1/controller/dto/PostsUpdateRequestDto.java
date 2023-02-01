package com.swcoaching.example1.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String title;
    private String content;
    private long boardId;

    @Builder
    public PostsUpdateRequestDto(String title, String content, long boardId) {
        this.title = title;
        this.content = content;
        this.boardId = boardId;
    }
}
