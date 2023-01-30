package com.swcoaching.example1.service.github;

import com.swcoaching.example1.domain.github.GithubData;
import com.swcoaching.example1.domain.github.GithubDataEntity;
import com.swcoaching.example1.domain.github.GithubDataRepository;
import org.springframework.stereotype.Service;

@Service
public class GithubDataServiceImpl implements GithubDataService {

  private final GithubDataRepository githubDataRepository;

  public GithubDataServiceImpl(GithubDataRepository githubDataRepository) {
    this.githubDataRepository = githubDataRepository;
  }

  @Override
  public GithubData findByGithubId(String githubId) {
    GithubDataEntity githubDataEntity = githubDataRepository.findByGithubId(githubId);
    return GithubData.of(githubDataEntity);
  }
}
