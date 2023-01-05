package com.swcoaching.example1.github;

import com.swcoaching.example1.github.jpa.GithubDataEntity;
import com.swcoaching.example1.github.jpa.GithubDataRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubDataServiceTest {

    @DisplayName("Github id로 회원 조회 한다")
    @Test
    void findByGithubId() {
        // given
        GithubDataEntity githubDataEntity = mock(GithubDataEntity.class);
        GithubDataRepository githubDataRepository = mock(GithubDataRepository.class);

        long testGithubDataId = 1L;
        String testGithubId = "SeoJeongYeop";
        int testYear = 2023;
        int testStarCount = 1;
        int testCommitCount = 100;
        int testPrCount = 1;
        int testIssueCount = 10;
        int testFollowerCount = 1;
        int testFollowingCount = 1;
        int testRepoCount = 10;

        when(githubDataEntity.getId()).thenReturn(testGithubDataId);
        when(githubDataEntity.getGithubId()).thenReturn(testGithubId);
        when(githubDataEntity.getYear()).thenReturn(testYear);
        when(githubDataEntity.getStarCount()).thenReturn(testStarCount);
        when(githubDataEntity.getCommitCount()).thenReturn(testCommitCount);
        when(githubDataEntity.getPrCount()).thenReturn(testPrCount);
        when(githubDataEntity.getIssueCount()).thenReturn(testIssueCount);
        when(githubDataEntity.getFollowerCount()).thenReturn(testFollowerCount);
        when(githubDataEntity.getFollowingCount()).thenReturn(testFollowingCount);
        when(githubDataEntity.getRepoCount()).thenReturn(testRepoCount);

        when(githubDataRepository.findByGithubId(testGithubId)).thenReturn(githubDataEntity);


        // when
        GithubDataService githubDataService = new GithubDataServiceImpl(githubDataRepository);
        GithubData githubData = githubDataService.findByGithubId(testGithubId);

        // then
        assertEquals(githubData.getId(), testGithubDataId);
        assertEquals(githubData.getGithubId(), testGithubId);
        assertEquals(githubData.getYear(), testYear);
        assertEquals(githubData.getStarCount(), testStarCount);
        assertEquals(githubData.getCommitCount(), testCommitCount);
        assertEquals(githubData.getPrCount(), testPrCount);
        assertEquals(githubData.getIssueCount(), testIssueCount);
        assertEquals(githubData.getFollowerCount(), testFollowerCount);
        assertEquals(githubData.getFollowingCount(), testFollowingCount);
        assertEquals(githubData.getRepoCount(), testRepoCount);
    }
}
