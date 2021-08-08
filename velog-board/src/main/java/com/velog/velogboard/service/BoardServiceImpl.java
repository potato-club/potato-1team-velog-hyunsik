package com.velog.velogboard.service;

import com.velog.velogboard.web.client.AuthServiceClient;
import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.board.repository.BoardRepository;
import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final AuthServiceClient authServiceClient;


    @Override
    public BoardResponse createBoard(BoardRequest request, String token) {
        return null;
    }

    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        return null;
    }
}
