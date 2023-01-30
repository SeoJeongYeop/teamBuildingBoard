package com.swcoaching.example1.domain.posts;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.board.BoardEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
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

    private String author;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private BoardEntity board;
}
