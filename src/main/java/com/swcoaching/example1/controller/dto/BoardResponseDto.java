package com.swcoaching.example1.controller.dto;

import com.swcoaching.example1.domain.board.BoardEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.format.DateTimeFormatter;


@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String remark;
    private final String createdDate;
    private final String modifiedDate;

    public BoardResponseDto(BoardEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.remark = entity.getRemark();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
