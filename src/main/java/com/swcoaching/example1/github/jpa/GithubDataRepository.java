package com.swcoaching.example1.github.jpa;

import org.springframework.data.repository.CrudRepository;

public interface GithubDataRepository extends CrudRepository<GithubDataEntity, Long> {

  GithubDataEntity findByGithubId(String githubId);
}
