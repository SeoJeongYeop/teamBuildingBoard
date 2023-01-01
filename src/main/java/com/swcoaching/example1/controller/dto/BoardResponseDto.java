package com.swcoaching.example1.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class BoardResponseDto {

    private final Long id;
    private final String title;
    private final String remark;
}
