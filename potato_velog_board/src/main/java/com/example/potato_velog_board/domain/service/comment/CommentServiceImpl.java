package com.example.potato_velog_board.domain.service.comment;

import com.example.potato_velog_board.domain.entity.board.Board;
import com.example.potato_velog_board.domain.entity.board.Comment;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.domain.repository.CommentRepository;
import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.request.board.CommentRequest;
import com.example.potato_velog_board.web.dto.response.user.UserFeignResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment writeComment(CommentRequest request, UserFeignResponse user, Long id) {
        final Comment comment = Comment.of(user.getUuid(), user.getNickName(), user.getThumbnail(), request.getContent());
        final Board board = boardRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_BOARD));
        board.addComment(comment);
        return commentRepository.save(comment);
    }
}
