package com.swcoaching.example1.domain.github;

import org.springframework.data.repository.CrudRepository;

public interface GithubDataRepository extends CrudRepository<GithubData, Long> {
    GithubData findByGithubUsername(String GithubUsername);
}
