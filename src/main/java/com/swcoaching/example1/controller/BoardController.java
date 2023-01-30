package com.swcoaching.example1.controller;

import com.swcoaching.example1.domain.board.Board;
import com.swcoaching.example1.service.board.BoardService;
import com.swcoaching.example1.controller.dto.BoardResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@AllArgsConstructor
@RestController
@RequestMapping
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BoardService boardService;

    @GetMapping("/api/v1/boards/{boardId}")
    public Board getBoard(@PathVariable long boardId) {
        Board board = boardService.findById(boardId);
        logger.info("Board: {}", board);
        return board;
    }

    // 연습용 코드
    @GetMapping("/api/v1/boards/dto")
    public BoardResponseDto boardDto(@RequestParam("id") Long id,
                                     @RequestParam("title") String title,
                                     @RequestParam("remark") String remark) {
        return new BoardResponseDto(id, title, remark);
    }

}
