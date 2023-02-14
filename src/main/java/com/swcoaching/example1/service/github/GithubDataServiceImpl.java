package com.swcoaching.example1.service.github;

import com.swcoaching.example1.domain.github.GithubData;
import com.swcoaching.example1.domain.github.GithubDataRepository;
import org.springframework.stereotype.Service;

@Service
public class GithubDataServiceImpl implements GithubDataService {

    private final GithubDataRepository githubDataRepository;

    public GithubDataServiceImpl(GithubDataRepository githubDataRepository) {
        this.githubDataRepository = githubDataRepository;
    }

    @Override
    public GithubData findByGithubUsername(String Username) {
        GithubData githubData = githubDataRepository.findByGithubUsername(Username);
        return githubData;
    }

    @Override
    public Long setUserId(String githubUsername, Long userId) {
        GithubData githubData = githubDataRepository.findByGithubUsername(githubUsername);
        githubData.setUserId(userId);
        return githubData.getId();
    }
}
