package com.swcoaching.example1.service.team;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;
import com.swcoaching.example1.controller.dto.TeamUpdateRequestDto;

import java.util.List;


public interface TeamService {
    Long save(TeamSaveRequestDto requestDto);

    Long update(Long id, TeamUpdateRequestDto requestDto);

    TeamResponseDto findById(Long id);

    List<TeamResponseDto> findAll();

    List<TeamResponseDto> findByUserIdDesc(Long userId);

    TeamResponseDto findByName(String teamName);
}
