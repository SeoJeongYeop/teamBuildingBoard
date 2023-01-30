package com.swcoaching.example1.domain.board;

import com.swcoaching.example1.domain.BaseTimeEntity;
import com.swcoaching.example1.domain.posts.PostsEntity;
import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
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

}
