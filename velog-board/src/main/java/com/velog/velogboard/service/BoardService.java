package com.velog.velogboard.service;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import javassist.NotFoundException;

import java.util.List;

public interface BoardService {

    BoardResponse createBoard(BoardRequest request, String token);

    List<BoardResponse> retrieveBoardList(String token);
}
