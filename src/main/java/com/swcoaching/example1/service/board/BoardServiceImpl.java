package com.swcoaching.example1.service.board;

import com.swcoaching.example1.controller.dto.BoardResponseDto;
import com.swcoaching.example1.domain.board.Board;
import com.swcoaching.example1.domain.board.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Board findById(long id) {
        return boardRepository.findById(id)
                .map(Board::of)
                .orElseThrow(() -> new BoardNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponseDto::new).collect(Collectors.toList());
    }
}
