package com.swcoaching.example1.domain.github;

import com.swcoaching.example1.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Table(name = "github_data")
@Entity
public class GithubData extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String githubUsername;
    @Column(name = "date_year")
    private int year;
    private int starCount;
    private int commitCount;
    private int prCount;
    private int issueCount;
    private int followerCount;
    private int followingCount;
    private int repoCount;
    private Long userId;

    @Builder
    public GithubData(Long id, String githubUsername, int year, int starCount, int commitCount, int prCount,
                      int issueCount, int followerCount, int followingCount, int repoCount) {
        this.id = id;
        this.githubUsername = githubUsername;
        this.year = year;
        this.starCount = starCount;
        this.commitCount = commitCount;
        this.prCount = prCount;
        this.issueCount = issueCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.repoCount = repoCount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
