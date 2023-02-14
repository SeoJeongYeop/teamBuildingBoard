package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.github.GithubData;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GithubConnectRequestDto {

    private String githubUsername;
    private Long userId;

    @Builder
    public GithubConnectRequestDto(String githubUsername, Long userId) {
        this.githubUsername = githubUsername;
        this.userId = userId;
    }

    public GithubData toEntity() {
        return GithubData.builder()
                .githubUsername(githubUsername)
                .build();
    }
}
