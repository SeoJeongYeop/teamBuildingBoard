package com.swcoaching.example1.service.github;

import com.swcoaching.example1.controller.dto.GithubResponseDto;

public interface GithubDataService {
    GithubResponseDto findByGithubUsername(String Username);

    Long setUserId(String githubUsername, Long userId);

    GithubResponseDto findByUserId(Long id);
}
