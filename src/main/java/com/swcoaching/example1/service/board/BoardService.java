package com.swcoaching.example1.service.board;

import com.swcoaching.example1.domain.board.Board;

public interface BoardService {
  Board findById(long id);
}
