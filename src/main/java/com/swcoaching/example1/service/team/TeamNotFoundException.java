package com.swcoaching.example1.service.team;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(long id) {
        super(String.format("id=(%s) 인 팀을 찾을 수 없습니다.", id));
    }
}
