package com.swcoaching.example1.service.user;

import com.swcoaching.example1.config.auth.dto.SessionUser;
import com.swcoaching.example1.controller.dto.UserResponseDto;
import com.swcoaching.example1.controller.dto.UserSaveRequestDto;
import com.swcoaching.example1.controller.dto.UserSignUpRequest;
import com.swcoaching.example1.domain.user.User;
import com.swcoaching.example1.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

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
    public UserResponseDto findByUsername(String username) {
        List<UserResponseDto> dtoList = userRepository.findByUsername(username).stream()
                .map(UserResponseDto::new).toList();

        if(dtoList.size() > 0) return dtoList.get(0);
        else return null;
    }

    @Override
    public UserResponseDto signUp(UserSignUpRequest signUpReq) throws Exception {
        if(this.isEmailExist(signUpReq.getEmail())) {
            throw new Exception("Your Mail already Exist.");
        }
        User newUser = signUpReq.toEntity();
        newUser.hashPassword(bCryptPasswordEncoder);
        User entity = userRepository.save(newUser);

        return new UserResponseDto(entity);
    }

    @Override
    public SessionUser login(Map<String, String> map) {
        System.out.println("Service impl ");
        System.out.println("username: "+ map.get("username") +
                "password: "+ map.get("password"));
        List<User> dtoList = userRepository.findByUsername(map.get("username"));
        if(dtoList != null) {
            User user = dtoList.get(0);
            if(bCryptPasswordEncoder.matches(map.get("password"), user.getPassword())){
                return new SessionUser(user);
            }
        }
        return null;
    }

    /**
     * 이메일 중복 여부를 확인
     *
     * @return true | false
     */
    private boolean isEmailExist(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }
}
