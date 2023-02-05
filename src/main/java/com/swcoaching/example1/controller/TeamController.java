package com.swcoaching.example1.controller;


import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.domain.relation.RelationStatus;
import com.swcoaching.example1.service.relation.UserTeamService;
import com.swcoaching.example1.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;
    private final UserTeamService userTeamService;


    @PostMapping("/api/v1/teams")
    public Long save(@RequestBody TeamSaveRequestDto requestDto) {
        Long teamId = teamService.save(requestDto);
        UserTeamSaveRequestDto entity = new UserTeamSaveRequestDto(requestDto.getUserId(), teamId, RelationStatus.IN);
        Long relationId = userTeamService.save(entity);
        System.out.println("teams save teamId=" + teamId + " relationId" + relationId);
        return teamId;
    }

    @PutMapping("/api/v1/teams/{id}")
    public Long update(@PathVariable Long id, @RequestBody TeamSaveRequestDto requestDto) {
        return teamService.update(id, requestDto);
    }

    @GetMapping("/api/v1/teams/{id}")
    public TeamResponseDto findById(@PathVariable Long id) {
        return teamService.findById(id);
    }

}
