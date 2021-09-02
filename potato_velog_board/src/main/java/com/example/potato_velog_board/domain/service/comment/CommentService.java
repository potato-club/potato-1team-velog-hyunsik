package com.example.potato_velog_board.domain.service.comment;

import com.example.potato_velog_board.domain.entity.board.Comment;
import com.example.potato_velog_board.web.dto.request.board.CommentRequest;
import com.example.potato_velog_board.web.dto.response.user.UserFeignResponse;

public interface CommentService {

    Comment writeComment(CommentRequest request, UserFeignResponse user, Long id);

}
