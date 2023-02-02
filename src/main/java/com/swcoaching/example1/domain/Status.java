package com.swcoaching.example1.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    NORMAL("STATUS_NORMAL", "정상"),
    BLOCK("STATUS_BLOCK", "차단"),
    DELETE("STATUS_DELETE", "삭제");

    private final String key;
    private final String title;
}
