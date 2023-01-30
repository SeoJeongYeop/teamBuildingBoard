package com.swcoaching.example1.domain.github;

import org.springframework.data.repository.CrudRepository;

public interface GithubDataRepository extends CrudRepository<GithubDataEntity, Long> {

  GithubDataEntity findByGithubId(String githubId);
}
