package com.swcoaching.example1.domain.relation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RelationStatus {

    IN("STATUS_IN", "소속"),
    WAIT("STATUS_WAIT", "대기"),
    QUIT("STATUS_QUIT", "탈퇴"),
    BLOCK("STATUS_BLOCK", "차단"),
    DELETE("STATUS_DELETE", "삭제");

    private final String key;
    private final String title;
}
