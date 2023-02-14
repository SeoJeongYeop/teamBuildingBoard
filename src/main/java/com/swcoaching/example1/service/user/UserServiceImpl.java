package com.swcoaching.example1.service.user;

import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserSaveRequestDto;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long save(UserSaveRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, UserSaveRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.update(requestDto.getName(), requestDto.getPicture());

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserResponseDto(entity);
    }

    @Override
    public void setUserImage(Long id, String imageBase) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        entity.setImage(imageBase);
    }
}
