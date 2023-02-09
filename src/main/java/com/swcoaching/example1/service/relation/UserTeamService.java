package com.swcoaching.example1.service.relation;

import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;

import java.util.List;

public interface UserTeamService {
    Long save(UserTeamSaveRequestDto requestDto, Long msgId);

    Long quitTeam(Long id, UserTeamSaveRequestDto requestDto);
    Long approveTeam(Long id);
    Long denyTeam(Long id);

    UserTeamResponseDto findById(Long id);

    List<UserTeamResponseDto> findByUserId(Long userId);

    List<UserTeamResponseDto> findWaitUserByTeamId(Long ownerTeamId);
}
