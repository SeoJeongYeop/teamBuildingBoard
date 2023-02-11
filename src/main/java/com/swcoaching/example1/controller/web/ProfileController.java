package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.PostsListResponseDto;
import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.posts.PostsService;
import com.swcoaching.example1.service.relation.UserTeamService;
import com.swcoaching.example1.service.team.TeamService;
import com.swcoaching.example1.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final UserService userService;
    private final BoardService boardService;
    private final PostsService postsService;
    private final TeamService teamService;
    private final UserTeamService userTeamService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        UserResponseDto dto = userService.findById(id);

        if (dto != null) {
            model.addAttribute("boards", boardService.findAll());

            List<PostsListResponseDto> postResDto = postsService.findByUserIdDesc(id);
            model.addAttribute("postsCount", (postResDto != null) ? postResDto.size() : 0);
            model.addAttribute("posts", postResDto);

            List<TeamResponseDto> teamResDto = teamService.findByUserIdDesc(id);
            model.addAttribute("teamsCount", (teamResDto != null) ? teamResDto.size() : 0);
            model.addAttribute("teams", teamResDto);

            List<UserTeamResponseDto> userTeamResDto = userTeamService.findByUserId(id);
            model.addAttribute("teamRelationsCount", (userTeamResDto != null) ? userTeamResDto.size() : 0);
            model.addAttribute("teamRelations", userTeamResDto);
            model.addAttribute("profile", dto);

            model.addAttribute("board", boardService.findById(id));
            if (user != null) {
                model.addAttribute("userName", user.getName());
                model.addAttribute("userPicture", user.getPicture());
                model.addAttribute("userId", user.getId());
            }
        }

        return "profile";
    }
}
