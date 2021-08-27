package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.web.dto.request.BoardRequest;
import com.example.potato_velog_board.web.dto.response.BoardResponse;

import java.util.List;

public interface BoardService {

    BoardResponse createBoard(BoardRequest request, String uuid);

    List<BoardResponse> retrieveBoardList(String token);
}
