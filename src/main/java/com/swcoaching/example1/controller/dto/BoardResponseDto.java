package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.board.BoardEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String remark;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public BoardResponseDto(BoardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.remark = entity.getRemark();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
