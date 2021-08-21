package com.velog.velogboard.service.board;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;

import java.util.List;

public interface BoardService {

    BoardResponse createBoard(BoardRequest request, Long userId);

    List<BoardResponse> retrieveBoardList(String token);
}
