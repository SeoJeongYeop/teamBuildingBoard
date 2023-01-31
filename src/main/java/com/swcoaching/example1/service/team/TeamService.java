package com.swcoaching.example1.service.team;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;


public interface TeamService {
    Long save(TeamSaveRequestDto requestDto);

    Long update(Long id, TeamSaveRequestDto requestDto);

    TeamResponseDto findById(Long id);
}
