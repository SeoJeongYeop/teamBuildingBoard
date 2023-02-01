package com.swcoaching.example1.domain.board;

import com.swcoaching.example1.domain.posts.PostsEntity;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class Board {
    private final Long id;

    private final String title;

    private final String remark;

    private final List<PostsEntity> posts;

    public Board(Long id, String title, String remark, List<PostsEntity> postsEntities) {
        this.id = id;
        this.title = title;
        this.remark = remark;
        this.posts = postsEntities;
    }

    public static Board of(BoardEntity boardEntity) {
        List<PostsEntity> posts = boardEntity.getPosts();
        return new Board(boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getRemark(),
                posts);
    }
}
