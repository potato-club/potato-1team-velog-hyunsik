package com.velog.velogboard.service.board;

import com.velog.velogboard.web.client.AuthServiceClient;
import com.velog.velogcommon.board.repository.BoardRepository;
import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.error.ErrorCode;
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
    private final UserRepository userRepository;
    private final AuthServiceClient authServiceClient;

    @Transactional
    @Override
    public BoardResponse createBoard(BoardRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        boardRepository.save(BoardServiceUtils.createBoard(request, user));
        return BoardResponse.of(BoardServiceUtils.createBoard(request, user));
    }

    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        return null;
    }
}
