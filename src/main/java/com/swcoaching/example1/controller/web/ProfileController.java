package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.*;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.github.GithubDataService;
import com.swcoaching.example1.service.posts.PostsService;
import com.swcoaching.example1.service.relation.UserTeamService;
import com.swcoaching.example1.service.team.TeamService;
import com.swcoaching.example1.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final UserService userService;
    private final BoardService boardService;
    private final PostsService postsService;
    private final TeamService teamService;
    private final UserTeamService userTeamService;
    private final GithubDataService githubDataService;

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

            String image = dto.getImage();
            if (image == null) {
                try {
                    byte[] imageByte = getImageByte(dto.getPicture());
                    String imageBase = Base64.getEncoder().encodeToString(imageByte);
                    userService.setUserImage(dto.getId(), imageBase);
                    dto.setImage(imageBase);
                } catch (IOException exception) {
                    logger.error("setUserModel" + exception);
                }
            } else {
                model.addAttribute("userImage", image);
            }

            model.addAttribute("profile", dto);
            setUserModel(model, user);
        }

        return "profile";
    }

    @GetMapping("/profile/{id}/github")
    public String profileGithub(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        UserResponseDto dto = userService.findById(id);

        if (dto != null) {
            model.addAttribute("boards", boardService.findAll());
            setUserModel(model, user);
        }

        return "profile-github";
    }

    private Model setUserModel(Model model, SessionUser user) {
        if (user == null) return model;
        model.addAttribute("userName", user.getName());
        model.addAttribute("userId", user.getId());
        String picture = user.getPicture();
        model.addAttribute("userPicture", picture);
        String image = user.getImage();
        if (image == null) {
            try {
                byte[] imageByte = getImageByte(picture);
                String imageBase = Base64.getEncoder().encodeToString(imageByte);
                userService.setUserImage(user.getId(), imageBase);
                model.addAttribute("userImage", imageBase);
            } catch (IOException exception) {
                logger.error("setProfileModel" + exception);
                return model;
            }
        } else {
            model.addAttribute("userImage", image);
        }
        return model;
    }

    private byte[] getImageByte(String picture) throws IOException {
        URL url = new URL(picture);
        return url.openStream().readAllBytes();
    }
}
