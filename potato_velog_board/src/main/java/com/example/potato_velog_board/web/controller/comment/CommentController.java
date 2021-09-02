package com.example.potato_velog_board.web.controller.comment;

import com.example.potato_velog_board.domain.entity.board.Comment;
import com.example.potato_velog_board.domain.service.comment.CommentService;
import com.example.potato_velog_board.utils.validation.ValidationUtils;
import com.example.potato_velog_board.web.client.UserServiceClient;
import com.example.potato_velog_board.web.dto.request.board.CommentRequest;
import com.example.potato_velog_board.web.dto.response.board.CommentResponse;
import com.example.potato_velog_board.web.dto.response.user.UserFeignResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserServiceClient userServiceClient;

    @PostMapping("writeComment/{boardId}")
    public ResponseEntity<CommentResponse> writeComment(@Valid @RequestBody CommentRequest request, BindingResult bindingResult,
                                                        @PathVariable("boardId") Long id,
                                                        @RequestHeader(name = "Authorization") String token) throws BindException {
        ValidationUtils.validateBindingResult(bindingResult);
        final UserFeignResponse user = userServiceClient.getUser(token);
        final Comment comment = commentService.writeComment(request, user, id);
        return ResponseEntity.ok(CommentResponse.of(comment));
    }


}
