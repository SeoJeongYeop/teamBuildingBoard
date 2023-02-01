package com.swcoaching.example1.domain.posts;


import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.board.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class Posts extends BaseTimeEntity {

    private Long id;
    private String title;
    private String content;
    private String author;
    private BoardEntity board;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    public static Posts of(PostsEntity postEntity) {
        return new Posts(postEntity.getTitle(), postEntity.getContent(), postEntity.getAuthor());
    }
}
