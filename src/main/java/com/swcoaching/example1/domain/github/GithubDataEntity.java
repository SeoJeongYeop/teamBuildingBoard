package com.swcoaching.example1.domain.github;

import jakarta.persistence.*;


@Table(name = "github_data")
@Entity
public class GithubDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String githubId;
    @Column(name = "nyear")
    private int year;
    private int starCount;
    @Column(name = "commit_count")
    private int commitCount;
    private int prCount;
    private int issueCount;
    private int followerCount;
    private int followingCount;
    private int repoCount;


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
