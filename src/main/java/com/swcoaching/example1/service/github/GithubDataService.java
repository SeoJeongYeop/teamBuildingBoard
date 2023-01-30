package com.swcoaching.example1.service.github;

import com.swcoaching.example1.domain.github.GithubData;

public interface GithubDataService {
    GithubData findByGithubId(String githubId);
}
