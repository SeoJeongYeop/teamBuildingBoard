package com.swcoaching.example1.service.board;

import com.swcoaching.example1.controller.dto.BoardResponseDto;
import com.swcoaching.example1.domain.board.Board;

import java.util.List;

public interface BoardService {
    Board findById(long id);

    List<BoardResponseDto> findAll();
}
