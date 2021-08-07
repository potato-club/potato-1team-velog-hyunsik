package com.velog.velogboard.service;

import com.velog.velogboard.web.client.AuthServiceClient;
import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.board.repository.BoardRepository;
import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import javassist.NotFoundException;
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

    @Transactional
    @Override
    public BoardResponse createBoard(BoardRequest request, String token) {
        String userId = authServiceClient.validateToken(token);
        return BoardResponse.of(boardRepository.save(Board.of(request.getTitle(), request.getContent(), userId)));
    }

    @Override
    public List<BoardResponse> retrieveBoardList(String token)  {
        String userId = authServiceClient.validateToken(token);
        return boardRepository.findBoardByUserId(userId).stream().map(BoardResponse::of).collect(Collectors.toList());
    }
}
