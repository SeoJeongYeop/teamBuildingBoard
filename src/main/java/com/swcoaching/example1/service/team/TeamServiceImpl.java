package com.swcoaching.example1.service.team;

import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;
import com.swcoaching.example1.domain.team.Team;
import com.swcoaching.example1.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    @Transactional
    public Long save(TeamSaveRequestDto requestDto) {
        return teamRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, TeamSaveRequestDto requestDto) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        team.update(requestDto.getName(), requestDto.getDescription(), requestDto.getPicture());
        return id;
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponseDto findById(Long id) {
        Team entity = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        return new TeamResponseDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamResponseDto> findAll() {
        return teamRepository.findAll().stream()
                .map(TeamResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TeamResponseDto findByName(String teamName) {
        List<Team> teams = teamRepository.findByName(teamName);
        if (teams.isEmpty()) throw new TeamNotFoundException(teamName);
        Team entity = teams.get(0);
        return new TeamResponseDto(entity);
    }
}
