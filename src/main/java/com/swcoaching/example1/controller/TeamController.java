package com.swcoaching.example1.controller;


import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.TeamSaveRequestDto;
import com.swcoaching.example1.controller.dto.TeamUpdateRequestDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.domain.relation.RelationStatus;
import com.swcoaching.example1.service.relation.UserTeamService;
import com.swcoaching.example1.service.team.TeamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class TeamController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TeamService teamService;
    private final UserTeamService userTeamService;

    @GetMapping("/api/v1/teams/{id}")
    public TeamResponseDto findById(@PathVariable Long id) {
        TeamResponseDto team = teamService.findById(id);
        logger.info("Team: {}", team);
        return team;
    }

    @PostMapping("/api/v1/teams")
    public Long save(@Valid @RequestBody TeamSaveRequestDto requestDto) {
        Long teamId = teamService.save(requestDto);
        UserTeamSaveRequestDto entity = new UserTeamSaveRequestDto(requestDto.getUserId(), teamId, RelationStatus.IN, null);
        Long relationId = userTeamService.save(entity, null);
        System.out.println("teams save teamId=" + teamId + " relationId" + relationId);
        return teamId;
    }

    @PutMapping("/api/v1/teams/{id}")
    public Long update(@PathVariable Long id, @Valid @RequestBody TeamUpdateRequestDto requestDto) {
        System.out.println("team update: " + id);
        return teamService.update(id, requestDto);
    }

}
