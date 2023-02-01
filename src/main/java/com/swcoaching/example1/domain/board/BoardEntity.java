package com.swcoaching.example1.domain.board;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.posts.PostsEntity;
import lombok.Builder;
import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table(name = "board")
@Entity
public class BoardEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 게시판 제목
     */
    @Column(length = 1000)
    private String title;

    /**
     * 게시판 설명
     */
    @Column(columnDefinition = "text")
    private String remark;

    /**
     * 게시물
     */
    @OneToMany(mappedBy = "board")
    public List<PostsEntity> posts = new ArrayList<>();

    @Builder
    public BoardEntity(String title, String remark) {
        this.title = title;
        this.remark = remark;
    }
    public void addPosts(PostsEntity posts) {
        this.posts.add(posts);
    }
}
