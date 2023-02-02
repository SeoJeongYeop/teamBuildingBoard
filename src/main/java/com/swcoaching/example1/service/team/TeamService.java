package com.swcoaching.example1.service.team;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;

import java.util.List;


public interface TeamService {
    Long save(TeamSaveRequestDto requestDto);

    Long update(Long id, TeamSaveRequestDto requestDto);

    TeamResponseDto findById(Long id);

    List<TeamResponseDto> findAll();

    TeamResponseDto findByName(String teamName);
}
