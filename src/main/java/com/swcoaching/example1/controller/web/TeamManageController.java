package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class TeamManageController {
    private final TeamService teamService;

    private final BoardService boardService;

    @GetMapping("/teams")
    public String teamList(Model model, @LoginUser SessionUser user) {
        /* TODO 이미지 파일이 있는지 체크하면 좋을 것 같다.*/
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("boards", boardService.findAll());

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        // 렌더링 요소
        model.addAttribute("title", "팀 둘러보기");
        model.addAttribute("createText", "팀 만들기");
        model.addAttribute("createLink", "/teams/save");

        return "teamList";
    }

    @GetMapping("/teams/{teamName}")
    public String team(@PathVariable String teamName, Model model, @LoginUser SessionUser user) {
        TeamResponseDto dto = teamService.findByName(teamName);
        model.addAttribute("team", dto);
        model.addAttribute("boards", boardService.findAll());

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
        }
        model.addAttribute("title", "팀 더보기");
        model.addAttribute("requestText", "지원하기");
        model.addAttribute("requestLink", "/teams/application/" + teamName);

        return "team";
    }
}
