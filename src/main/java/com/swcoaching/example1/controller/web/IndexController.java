package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.PostsResponseDto;
import com.swcoaching.example1.domain.board.Board;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.posts.PostsService;
import com.swcoaching.example1.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URL;
import java.util.Base64;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PostsService postsService;
    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그때마다 직접 세션에서 값을 가져와야한다.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // -> @LoginUser 로 개선한다.
        setUserModel(model, user);
        // postService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("boards", boardService.findAll());
        model.addAttribute("posts", postsService.findAllDesc());

        model.addAttribute("Title", "메인 페이지");
        model.addAttribute("TitleLink", "/");
        model.addAttribute("defaultImage", defaultImage.DEFAULT_PROFILE);

        return "index";
    }

    @GetMapping("/community")
    public String community(Model model, @LoginUser SessionUser user) {
        setUserModel(model, user);
        model.addAttribute("boards", boardService.findAll());

        return "board";
    }

    @GetMapping("/community/boards/{boardId}")
    public String board(@PathVariable Long boardId, Model model, @LoginUser SessionUser user) {
        setUserModel(model, user);
        model.addAttribute("boards", boardService.findAll());
        Board dto = boardService.findById(boardId);
        model.addAttribute("posts", postsService.findByBoardId(boardId));
        model.addAttribute("Title", dto.getTitle());
        model.addAttribute("TitleLink", "/community/boards/" + dto.getId());
        model.addAttribute("saveLink", "/community/posts/save" + "?id=" + boardId);
        model.addAttribute("defaultImage", defaultImage.DEFAULT_PROFILE);

        return "index";
    }

    @GetMapping("/community/posts/{id}")
    public String posts(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("boards", boardService.findAll());
        PostsResponseDto dto = postsService.findById(id);

        // check author
        dto.checkAuthor(user.getId());
        logger.info("PostsResponseDto isAuthor");
        logger.info("isAuthor: " + dto.isAuthor());
        setUserModel(model, user);
        model.addAttribute("post", dto);

        return "post";
    }

    // 모델 애트리뷰트
    @GetMapping("/community/posts/save")
    public String postsSave(@RequestParam(value = "id", required = false) Long id, Model model, @LoginUser SessionUser user) {
        setUserModel(model, user);
        model.addAttribute("boards", boardService.findAll());

        if (id != null)
            model.addAttribute("board", boardService.findById(id));

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        setUserModel(model, user);
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        model.addAttribute("boards", boardService.findAll());
        if (id != null)
            model.addAttribute("board", boardService.findById(id));

        return "posts-update";
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
