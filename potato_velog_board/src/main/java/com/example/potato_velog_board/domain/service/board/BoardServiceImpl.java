package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.board.Board;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.web.client.UserServiceClient;
import com.example.potato_velog_board.web.dto.request.board.BoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    @Override
    public Board createBoard(BoardRequest request, String uuid) {
        return boardRepository.save(BoardServiceUtils.createBoard(request, uuid));
    }

    @Transactional
    @Override
    public Board updateBoard(BoardRequest request, Long id, String uuid) {
        final Board board = BoardServiceUtils.validateExistBoard(boardRepository, id, uuid);
        return boardRepository.save(BoardServiceUtils.updateBoard(request, board));
    }

    @Transactional
    @Override
    public String deleteBoard(Long id, String uuid) {
        final Board board = BoardServiceUtils.validateExistBoard(boardRepository, id, uuid);
        boardRepository.delete(board);
        return "게시글 삭제 성공 id = " + id;
    }


}
