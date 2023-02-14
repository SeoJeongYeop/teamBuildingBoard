package com.swcoaching.example1.service.user;

import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserSaveRequestDto;


public interface UserService {
    Long save(UserSaveRequestDto requestDto);

    Long update(Long id, UserSaveRequestDto requestDto);

    UserResponseDto findById(Long id);

    void setUserImage(Long id, String imageBase);
}
