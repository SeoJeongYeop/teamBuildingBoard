package com.swcoaching.example1.controller;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.GithubConnectRequestDto;
import com.swcoaching.example1.service.github.GithubDataService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping
public class GithubController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GithubDataService githubDataService;

    @PostMapping("api/v1/profile-github/save")
    public Long profileGithubSave(@LoginUser SessionUser user, @Valid @RequestBody GithubConnectRequestDto requestDto) {
        logger.info("profileGithubSave: " + requestDto.getGithubUsername());
        logger.info("profileGithubSave: " + requestDto.getUserId());

        return githubDataService.setUserId(requestDto.getGithubUsername(), user.getId());
    }
}
