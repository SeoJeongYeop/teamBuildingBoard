package com.swcoaching.example1.domain.relation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTeamRepository extends JpaRepository<UserTeamRelation, Long> {

}
