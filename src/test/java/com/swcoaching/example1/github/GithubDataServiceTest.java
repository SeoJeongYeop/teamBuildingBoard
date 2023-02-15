package com.swcoaching.example1.github;

import com.swcoaching.example1.controller.dto.GithubResponseDto;
import com.swcoaching.example1.domain.github.GithubData;
import com.swcoaching.example1.domain.github.GithubDataRepository;
import com.swcoaching.example1.service.github.GithubDataService;
import com.swcoaching.example1.service.github.GithubDataServiceImpl;
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
        GithubData githubDataEntity = mock(GithubData.class);
        GithubDataRepository githubDataRepository = mock(GithubDataRepository.class);

        Long testGithubDataId = 1L;
        String testGithubUsername = "SeoJeongYeop";
        Long testYear = 2023L;
        Long testStarCount = 1L;
        Long testCommitCount = 100L;
        Long testPrCount = 1L;
        Long testIssueCount = 10L;
        Long testFollowerCount = 1L;
        Long testFollowingCount = 1L;
        Long testRepoCount = 10L;

        when(githubDataEntity.getId()).thenReturn(testGithubDataId);
        when(githubDataEntity.getGithubUsername()).thenReturn(testGithubUsername);
        when(githubDataEntity.getYear()).thenReturn(testYear);
        when(githubDataEntity.getStarCount()).thenReturn(testStarCount);
        when(githubDataEntity.getCommitCount()).thenReturn(testCommitCount);
        when(githubDataEntity.getPrCount()).thenReturn(testPrCount);
        when(githubDataEntity.getIssueCount()).thenReturn(testIssueCount);
        when(githubDataEntity.getFollowerCount()).thenReturn(testFollowerCount);
        when(githubDataEntity.getFollowingCount()).thenReturn(testFollowingCount);
        when(githubDataEntity.getRepoCount()).thenReturn(testRepoCount);

        when(githubDataRepository.findByGithubUsername(testGithubUsername))
                .thenReturn(githubDataEntity);


        // when
        GithubDataService githubDataService = new GithubDataServiceImpl(githubDataRepository);
        GithubResponseDto githubData = githubDataService.findByGithubUsername(testGithubUsername);

        // then
        assertEquals(githubData.getId(), testGithubDataId);
        assertEquals(githubData.getGithubUsername(), testGithubUsername);
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
