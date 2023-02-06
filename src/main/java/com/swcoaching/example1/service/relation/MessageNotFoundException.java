package com.swcoaching.example1.service.relation;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(long id) {
        super(String.format("id=(%s) 인 메시지를 찾을 수 없습니다.", id));
    }
}
