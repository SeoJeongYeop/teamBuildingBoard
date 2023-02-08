package com.swcoaching.example1.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    List<Team> findByName(String teamName);

    @Query("SELECT t FROM Team t WHERE t.owner.id = ?1 ORDER BY t.id DESC")
    List<Team> findByUserIdDesc(Long userId);
}
