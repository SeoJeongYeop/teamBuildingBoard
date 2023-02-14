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
    private Long year;
    private Long starCount;
    private Long commitCount;
    private Long prCount;
    private Long issueCount;
    private Long followerCount;
    private Long followingCount;
    private Long repoCount;
    private Long userId;

    @Builder
    public GithubData(Long id, String githubUsername, Long year, Long starCount, Long commitCount, Long prCount,
                      Long issueCount, Long followerCount, Long followingCount, Long repoCount) {
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
