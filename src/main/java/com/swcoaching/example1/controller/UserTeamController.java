package com.swcoaching.example1.controller;

import com.swcoaching.example1.controller.dto.MessageSaveRequestDto;
import com.swcoaching.example1.controller.dto.UserTeamIdDto;
import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.service.relation.MessageService;
import com.swcoaching.example1.service.relation.UserTeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserTeamController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MessageService messageService;
    private final UserTeamService userTeamService;

    @PostMapping("/api/v1/user-team-relations")
    public Long save(@Valid @RequestBody UserTeamSaveRequestDto requestDto) {
        logger.info("apply Team: " + requestDto.getContent());
        Long msgId = messageService.save(new MessageSaveRequestDto(requestDto.getContent(), requestDto.getUserId()));
        logger.info("msgId: " + msgId);

        return userTeamService.save(requestDto, msgId);
    }

    @PostMapping("/api/v1/user-team-relations/approve")
    public UserTeamResponseDto approve(@Valid @RequestBody UserTeamIdDto requestDto) {
        logger.info("approve id: " + requestDto.getId());
        Long id = userTeamService.approveTeam(requestDto.getId());

        return userTeamService.findById(id);
    }

    @PostMapping("/api/v1/user-team-relations/deny")
    public UserTeamResponseDto deny(@Valid @RequestBody UserTeamIdDto requestDto) {
        logger.info("deny id: " + requestDto.getId());
        Long id = userTeamService.denyTeam(requestDto.getId());

        return userTeamService.findById(id);
    }

    @GetMapping("/api/v1/user-team-relations/{id}")
    public UserTeamResponseDto findById(@PathVariable Long id) {
        logger.info("GetMapping: id=" + id);

        return userTeamService.findById(id);
    }
}
