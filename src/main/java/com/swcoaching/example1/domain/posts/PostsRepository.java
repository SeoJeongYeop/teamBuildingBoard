package com.swcoaching.example1.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
    @Query("SELECT p FROM PostsEntity p ORDER BY p.id DESC")
    List<PostsEntity> findAllDesc();

    @Query("SELECT p FROM PostsEntity p WHERE p.board.id = ?1 ORDER BY p.id DESC")
    List<PostsEntity> findByBoardId(Long boardId);

    @Query("SELECT p FROM PostsEntity p WHERE p.user.id = ?1 ORDER BY p.id DESC")
    List<PostsEntity> findByUserIdDesc(Long userId);
}
