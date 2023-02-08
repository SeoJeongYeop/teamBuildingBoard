package com.swcoaching.example1.controller;

import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserSignUpRequest;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping("/account/login")
    public Model login(@RequestParam Map<String, String> map, Model model, HttpSession session) {
        System.out.println("PostMapping login");
        try {
            if (map.get("username") == null || map.get("password") == null) {
                model.addAttribute("msg", "아이디 또는 비밀번호를 입력해주세요");
                return model;
            }
            SessionUser user = userService.login(map);
            if (user != null) {
                session.setAttribute("user", user);
            } else {
                model.addAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return model;
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "로그인 중 문제가 발생했습니다.");
            return model;
        }
        model.addAttribute("msg", "로그인 성공!");
        model.addAttribute("status", "success");
        return model;
    }
    @PostMapping("/api/v1/register/save")
    public UserResponseDto signUp(@ModelAttribute @Validated UserSignUpRequest signUpReq) throws Exception {
        return userService.signUp(signUpReq);
    }
    @GetMapping("/api/v1/register/check-username/{username}")
    public UserResponseDto checkUserName(@PathVariable String username) {
        
        UserResponseDto user = userService.findByUsername(username);
        System.out.println("여기까지");
        return user;
    }
}
