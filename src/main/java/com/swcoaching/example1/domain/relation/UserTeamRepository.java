package com.swcoaching.example1.domain.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeamRelation, Long> {
    @Query("SELECT t FROM UserTeamRelation t WHERE t.user.id = ?1")
    List<UserTeamRelation> findByUserId(Long userId);

}
