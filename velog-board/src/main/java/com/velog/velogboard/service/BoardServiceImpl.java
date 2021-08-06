package com.velog.velogboard.service;

import com.velog.velogboard.domain.entity.Board;
import com.velog.velogboard.jwt.TokenProvider;
import com.velog.velogboard.repository.BoardRepository;
import com.velog.velogboard.service.BoardService;
import com.velog.velogboard.web.client.UserServiceClient;
import com.velog.velogboard.web.dto.request.BoardRequest;
import com.velog.velogboard.web.dto.response.BoardResponse;
import com.velog.velogboard.web.dto.response.UserIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public BoardResponse createBoard(BoardRequest request, String token) {
        String userId = tokenProvider.validateTokenAndGetUserId(token);
        return new BoardResponse(boardRepository.save(Board.of(request.getTitle(), request.getContent(), userId)));
    }
}
