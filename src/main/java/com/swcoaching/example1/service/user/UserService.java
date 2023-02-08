package com.swcoaching.example1.service.user;

import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserSaveRequestDto;
import com.swcoaching.example1.controller.dto.UserSignUpRequest;

import java.util.Map;


public interface UserService {
    Long save(UserSaveRequestDto requestDto);
    Long update(Long id, UserSaveRequestDto requestDto);
    UserResponseDto findById(Long id);

    UserResponseDto findByUsername(String username);

    UserResponseDto signUp(UserSignUpRequest signUpReq) throws Exception;

    SessionUser login(Map<String, String> map);
}
