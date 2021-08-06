package com.velog.velogboard.service;

import com.velog.velogboard.domain.entity.Board;
import com.velog.velogboard.web.dto.request.BoardRequest;
import com.velog.velogboard.web.dto.response.BoardResponse;
import javassist.NotFoundException;

import java.util.List;

public interface BoardService {

    BoardResponse createBoard(BoardRequest request, String token);

    List<BoardResponse> retrieveBoardList(String token);
}
