package com.swcoaching.example1.service.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super(String.format("id=(%s) 인 유저를 찾을 수 없습니다.", id));
    }
}
