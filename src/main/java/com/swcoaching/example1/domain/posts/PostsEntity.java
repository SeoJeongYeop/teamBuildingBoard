package com.swcoaching.example1.domain.posts;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.board.BoardEntity;
import com.swcoaching.example1.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "post")
@Entity
public class PostsEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;

    @Builder
    public PostsEntity(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setBoard(BoardEntity boardEntity) {
        this.board = boardEntity;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
