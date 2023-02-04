package com.swcoaching.example1.controller;

import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamSaveRequestDto;
import com.swcoaching.example1.service.relation.UserTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserTeamController {
    private final UserTeamService userTeamService;

    @PostMapping("/api/v1/user-team-relations")
    public Long save(@RequestBody UserTeamSaveRequestDto requestDto) {
        return userTeamService.save(requestDto);
    }

    @GetMapping("/api/v1/user-team-relations/{id}")
    public UserTeamResponseDto findById(@PathVariable Long id) {
        System.out.println("GetMapping: id=" + id);

        UserTeamResponseDto dto = userTeamService.findById(id);
        System.out.println("dto " +dto);
        return userTeamService.findById(id);
    }
}
