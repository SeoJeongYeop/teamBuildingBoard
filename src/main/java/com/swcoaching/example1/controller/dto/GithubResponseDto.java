package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.github.GithubData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GithubResponseDto {
    private final Long id;
    private final String githubUsername;
    private final Long year;
    private final Long starCount;
    private final Long commitCount;
    private final Long prCount;
    private final Long issueCount;
    private final Long followerCount;
    private final Long followingCount;
    private final Long repoCount;
    private final Long userId;

    public GithubResponseDto(GithubData entity) {
        this.id = entity.getId();
        this.githubUsername = entity.getGithubUsername();
        this.year = entity.getYear();
        this.starCount = entity.getStarCount();
        this.commitCount = entity.getCommitCount();
        this.prCount = entity.getPrCount();
        this.issueCount = entity.getIssueCount();
        this.followerCount = entity.getFollowerCount();
        this.followingCount = entity.getFollowingCount();
        this.repoCount = entity.getRepoCount();
        this.userId = entity.getUserId();
    }
}
