package com.swcoaching.example1.github;
import com.swcoaching.example1.github.jpa.GithubDataEntity;

import lombok.Builder;

public class GithubData {
    private final Long id;
    private final String githubId;
    private final int year;
    private final int starCount;
    private final int commitCount;
    private final int prCount;
    private final int issueCount;
    private final int followerCount;
    private final int followingCount;
    private final int repoCount;


    @Builder
    public GithubData(Long id, String githubId, int year, int starCount, int commitCount, int prCount,
                      int issueCount, int followerCount, int followingCount, int repoCount) {
        this.id = id;
        this.githubId = githubId;
        this.year = year;
        this.starCount = starCount;
        this.commitCount = commitCount;
        this.prCount = prCount;
        this.issueCount = issueCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.repoCount = repoCount;
    }

    public static GithubData of(GithubDataEntity githubDataEntity) {
        return new GithubData(githubDataEntity.getId(),
                githubDataEntity.getGithubId(),
                githubDataEntity.getYear(),
                githubDataEntity.getStarCount(),
                githubDataEntity.getCommitCount(),
                githubDataEntity.getPrCount(),
                githubDataEntity.getIssueCount(),
                githubDataEntity.getFollowerCount(),
                githubDataEntity.getFollowingCount(),
                githubDataEntity.getRepoCount());
    }

    public Long getId() {
        return id;
    }

    public String getGithubId() {
        return githubId;
    }

    public int getYear() {
        return year;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getCommitCount() {
        return commitCount;
    }

    public int getPrCount() {
        return prCount;
    }

    public int getIssueCount() {
        return issueCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getRepoCount() {
        return repoCount;
    }
}
