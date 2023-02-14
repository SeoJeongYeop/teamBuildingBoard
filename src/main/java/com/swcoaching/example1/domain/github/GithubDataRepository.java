package com.swcoaching.example1.domain.github;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GithubDataRepository extends JpaRepository<GithubData, Long> {
    GithubData findByGithubUsername(String GithubUsername);

    @Query("SELECT g FROM GithubData g WHERE g.userId = ?1")
    GithubData findByUserId(Long userId);
}
