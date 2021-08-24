package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.web.client.UserServiceClient;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import com.example.potato_velog_board.web.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    @Override
    public BoardResponse createBoard(BoardRequest request, Long userId) {
        return BoardResponse.of(boardRepository.save(BoardServiceUtils.createBoard(request, userId)));
    }

    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        return null;
    }
}
