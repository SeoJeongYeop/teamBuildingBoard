package com.swcoaching.example1.domain.posts;


import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.board.BoardEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public static Posts of(PostsEntity postEntity) {
        return new Posts(postEntity.getTitle(), postEntity.getContent(), postEntity.getAuthor());
    }
}
