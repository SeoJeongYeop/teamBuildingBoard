package com.swcoaching.example1.controller.web;

import com.swcoaching.example1.config.auth.LoginUser;
import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.PostsResponseDto;
import com.swcoaching.example1.domain.board.Board;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        // postService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달
        model.addAttribute("boards", boardService.findAll());
        model.addAttribute("posts", postsService.findAllDesc());

        //index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그때마다 직접 세션에서 값을 가져와야한다.
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // -> @LoginUser 로 개선한다.

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }
        model.addAttribute("Title", "메인 페이지");
        model.addAttribute("TitleLink", "/");

        return "index";
    }

    @GetMapping("/community")
    public String community(Model model, @LoginUser SessionUser user) {

        model.addAttribute("boards", boardService.findAll());
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }

        return "board";
    }

    @GetMapping("/community/boards/{boardId}")
    public String board(@PathVariable Long boardId, Model model, @LoginUser SessionUser user) {

        model.addAttribute("boards", boardService.findAll());
        Board dto = boardService.findById(boardId);
        model.addAttribute("posts", postsService.findByBoardId(boardId));

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }
        model.addAttribute("Title", dto.getTitle());
        model.addAttribute("TitleLink", "/community/boards/" + dto.getId());
        model.addAttribute("saveLink", "/community/posts/save" + "?id=" + boardId);
        return "index";
    }

    @GetMapping("/community/posts/{id}")
    public String posts(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("boards", boardService.findAll());
        PostsResponseDto dto = postsService.findById(id);
        if (user != null) {
            dto.checkAuthor(user.getId());
            System.out.println("PostsResponseDto isAuthor");
            System.out.println(dto.isAuthor());
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }
        model.addAttribute("post", dto);

        return "post";
    }

    @GetMapping("/community/posts/save")
    public String postsSave(@RequestParam(value = "id", required = false) Long id, Model model, @LoginUser SessionUser user) {
        model.addAttribute("boards", boardService.findAll());
        if (id != null)
            model.addAttribute("board", boardService.findById(id));

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        model.addAttribute("boards", boardService.findAll());
        if (id != null)
            model.addAttribute("board", boardService.findById(id));
        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userPicture", user.getPicture());
            model.addAttribute("userId", user.getId());
        }

        return "posts-update";
    }

    @GetMapping("/account/login")
    public String login() {
        return "login";
    }
    @GetMapping("/account/register")
    public String register() {
        return "register";
    }
}
