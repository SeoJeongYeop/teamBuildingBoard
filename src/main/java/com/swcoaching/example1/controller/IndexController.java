package com.swcoaching.example1.controller;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.PostsResponseDto;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // postService.findAllDesc()로 가져온 결과를 posts로 index.mustach에 전달
        model.addAttribute("posts", postsService.findAllDesc());

        //index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그때마다 직접 세션에서 값을 가져와야한다.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // -> @LoginUser로 개선한다.

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/community")
    public String community(Model model, @LoginUser SessionUser user) {

        model.addAttribute("boards", boardService.findAll());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "board";
    }

    @GetMapping("/posts/save")
    public String postsSave(Model model, @LoginUser SessionUser user) {
        model.addAttribute("boards", boardService.findAll());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        model.addAttribute("boards", boardService.findAll());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "posts-update";
    }
}
