package com.swcoaching.example1.service.github;

import com.swcoaching.example1.domain.github.GithubData;

public interface GithubDataService {
    GithubData findByGithubUsername(String Username);

    Long setUserId(String githubUsername, Long userId);
}
