package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.MessageResponseDto;
import com.swcoaching.example1.controller.dto.TeamReqListResDto;
import com.swcoaching.example1.controller.dto.TeamResponseDto;
import com.swcoaching.example1.controller.dto.UserTeamResponseDto;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.relation.MessageService;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class TeamManageController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final TeamService teamService;
    private final BoardService boardService;
    private final UserTeamService userTeamService;
    private final MessageService messageService;
    private final UserService userService;


    @GetMapping("/teams")
    public String teamList(Model model, @LoginUser SessionUser user) {
        /* TODO 이미지 파일이 있는지 체크하면 좋을 것 같다.*/
        model.addAttribute("teams", teamService.findAll());
        model.addAttribute("boards", boardService.findAll());

        setUserModel(model, user);
        // 렌더링 요소
        model.addAttribute("title", "팀 둘러보기");
        model.addAttribute("createText", "팀 만들기");
        model.addAttribute("createLink", "/teams/save");

        return "teamList";
    }

    @GetMapping("/teams/{teamId}")
    public String team(@PathVariable Long teamId, Model model, @LoginUser SessionUser user) {
        TeamResponseDto dto = teamService.findById(teamId);
        model.addAttribute("boards", boardService.findAll());

        dto.checkOwner(user.getId());
        logger.info("TeamResponseDto isOwner");
        logger.info("isOwner" + dto.isOwner());
        setUserModel(model, user);
        model.addAttribute("team", dto);
        model.addAttribute("title", "팀 둘러보기");
        model.addAttribute("requestText", "지원하기");
        model.addAttribute("requestLink", "/teams/application/" + teamId);

        return "team";
    }

    @GetMapping("/teams/application/{teamId}")
    public String teamApplication(@PathVariable Long teamId, Model model, @LoginUser SessionUser user) {
        TeamResponseDto dto = teamService.findById(teamId);
        model.addAttribute("boards", boardService.findAll());

        setUserModel(model, user);
        model.addAttribute("team", dto);

        return "team-application";
    }

    @GetMapping("/teams/requests")
    public String teamRequestsList(Model model, @LoginUser SessionUser user) {
        List<TeamResponseDto> teamList = teamService.findByUserIdDesc(user.getId());

        List<TeamReqListResDto> result = new ArrayList<>();
        for (TeamResponseDto teamResDto : teamList) {
            Long ownerTeamId = teamResDto.getId();
            List<UserTeamResponseDto> relationDtoList = userTeamService.findWaitUserByTeamId(ownerTeamId);
            for (UserTeamResponseDto userTeamResDto : relationDtoList) {
                Long targetUserId = userTeamResDto.getUser().getId();
                List<MessageResponseDto> msgList = messageService.findByUserId(targetUserId);
                for (MessageResponseDto messageResDto : msgList) {
                    result.add(new TeamReqListResDto(messageResDto, userTeamResDto));
                }
            }
        }
        model.addAttribute("msgs", result);
        model.addAttribute("hasMsgs", result.size() != 0);
        model.addAttribute("boards", boardService.findAll());

        setUserModel(model, user);

        return "team-request";
    }

    // 1. 공통으로 안가져가게 공통으로 api로 할수있도록
    // 2. 캐시하는 방법
    // 3. 아규먼트 리졸브 계속읽어오는것
    // 4. 사용은 잘 안하는데 세션에 넣는 방법


    @GetMapping("/teams/requests/{teamId}")
    public String teamRequest(@PathVariable Long teamId, Model model, @LoginUser SessionUser user) {
        TeamResponseDto dto = teamService.findById(teamId);
        model.addAttribute("boards", boardService.findAll());

        setUserModel(model, user);
        model.addAttribute("team", dto);

        return "team-application";
    }

    @GetMapping("/teams/save")
    public String teamSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("boards", boardService.findAll());
        setUserModel(model, user);

        return "team-save";
    }

    @GetMapping("/teams/update/{teamId}")
    public String teamUpdate(@PathVariable Long teamId, Model model, @LoginUser SessionUser user) {
        logger.info("teamId=" + teamId);
        TeamResponseDto dto = teamService.findById(teamId);
        logger.info("teamName=" + dto.getName());
        model.addAttribute("team", dto);
        model.addAttribute("boards", boardService.findAll());
        setUserModel(model, user);

        return "team-update";
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
                logger.error("setUserModel" + exception);
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
