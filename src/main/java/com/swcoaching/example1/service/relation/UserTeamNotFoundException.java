package com.swcoaching.example1.service.relation;

public class UserTeamNotFoundException extends RuntimeException {
    public UserTeamNotFoundException(long id) {
        super(String.format("id=(%s) 인 유저-팀 관계를 찾을 수 없습니다.", id));
    }
}
