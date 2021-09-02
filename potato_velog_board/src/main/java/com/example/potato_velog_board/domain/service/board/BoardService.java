package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.board.Board;
import com.example.potato_velog_board.web.dto.request.board.BoardRequest;

public interface BoardService {

    Board createBoard(BoardRequest request, String uuid);

    Board updateBoard(BoardRequest request, Long id, String uuid);

    String deleteBoard(Long id, String uuid);

}
