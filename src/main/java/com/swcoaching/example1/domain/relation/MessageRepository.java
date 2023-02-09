package com.swcoaching.example1.domain.relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT t FROM Message t WHERE t.user.id = ?1")
    List<Message> findByUserId(Long targetUserId);
}
