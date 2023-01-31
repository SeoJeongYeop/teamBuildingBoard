package com.swcoaching.example1.service.posts;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(long id) {
        super(String.format("id=(%s) 인 포스트를 찾을 수 없습니다.", id));
    }
}
