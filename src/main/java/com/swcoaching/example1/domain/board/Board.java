package com.swcoaching.example1.domain.board;

import com.swcoaching.example1.domain.posts.Posts;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
public class Board {
    private final Long id;

    private final String title;

    private final String remark;

    private final List<Posts> posts;

    public Board(Long id, String title, String remark, List<Posts> posts) {
        this.id = id;
        this.title = title;
        this.remark = remark;
        this.posts = posts;
    }

    public static Board of(BoardEntity boardEntity) {
        List<Posts> posts = boardEntity.getPosts()
                .stream().map(Posts::of).collect(Collectors.toList());
        return new Board(boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getRemark(),
                posts);
    }
}
