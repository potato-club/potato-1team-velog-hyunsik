package com.velog.velogboard.service;

import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.board.repository.BoardRepository;
import com.velog.velogboard.web.client.UserServiceClient;
import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    @Override
    public BoardResponse createBoard(BoardRequest request, String token) {
        String userId = userServiceClient.validateToken(token);
        return BoardResponse.of(boardRepository.save(Board.of(request.getTitle(), request.getContent(), userId)));
    }

    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        String userId = userServiceClient.validateToken(token);
        return boardRepository.findBoardByUserId(userId).stream().map(BoardResponse::of).collect(Collectors.toList());
    }
}
