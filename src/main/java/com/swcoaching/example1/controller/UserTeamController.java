package com.swcoaching.example1.controller;

import com.swcoaching.example1.controller.dto.MessageSaveRequestDto;
import com.swcoaching.example1.controller.dto.UserTeamIdDto;
import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.service.relation.MessageService;
import com.swcoaching.example1.service.relation.UserTeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserTeamController {
    private final MessageService messageService;

    private final UserTeamService userTeamService;

    @PostMapping("/api/v1/user-team-relations")
    public Long save(@Valid @RequestBody UserTeamSaveRequestDto requestDto) {
        System.out.println("apply Team: " + requestDto.getContent());
        Long msgId = messageService.save(new MessageSaveRequestDto(requestDto.getContent(), requestDto.getUserId()));
        System.out.println("msgId: " + msgId);

        return userTeamService.save(requestDto, msgId);
    }

    @PostMapping("/api/v1/user-team-relations/approve")
    public UserTeamResponseDto approve(@Valid @RequestBody UserTeamIdDto requestDto) {
        System.out.println("approve id: " + requestDto.getId());
        Long id = userTeamService.approveTeam(requestDto.getId());

        return userTeamService.findById(id);
    }

    @GetMapping("/api/v1/user-team-relations/{id}")
    public UserTeamResponseDto findById(@PathVariable Long id) {
        System.out.println("GetMapping: id=" + id);

        return userTeamService.findById(id);
    }
}
