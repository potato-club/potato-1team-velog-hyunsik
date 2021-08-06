package com.velog.velogboard.service;

import com.velog.velogboard.web.dto.request.BoardRequest;
import com.velog.velogboard.web.dto.response.BoardResponse;

public interface BoardService {

    BoardResponse createBoard(BoardRequest request, String token);
}
