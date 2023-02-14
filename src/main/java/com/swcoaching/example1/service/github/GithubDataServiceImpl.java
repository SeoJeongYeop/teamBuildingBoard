package com.swcoaching.example1.service.github;

import com.swcoaching.example1.controller.dto.GithubResponseDto;
import com.swcoaching.example1.domain.github.GithubData;
import com.swcoaching.example1.domain.github.GithubDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GithubDataServiceImpl implements GithubDataService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GithubDataRepository githubDataRepository;

    public GithubDataServiceImpl(GithubDataRepository githubDataRepository) {
        this.githubDataRepository = githubDataRepository;
    }

    @Override
    public GithubResponseDto findByGithubUsername(String Username) {
        GithubData entity = githubDataRepository.findByGithubUsername(Username);
        return new GithubResponseDto(entity);
    }

    @Override
    public Long setUserId(String githubUsername, Long userId) {
        logger.info("setUserId: " + userId);

        GithubData githubData = githubDataRepository.findByGithubUsername(githubUsername);
        logger.info("githubData: " + githubData.getGithubUsername());
        logger.info("githubData: " + githubData.getUserId());
        if (githubData.getUserId() == null)
            githubData.setUserId(userId);
        else return githubData.getUserId();
        return userId;
    }

    @Override
    public GithubResponseDto findByUserId(Long id) {
        GithubData entity = githubDataRepository.findByUserId(id);
        if (entity != null)
            return new GithubResponseDto(entity);
        return null;
    }
}
