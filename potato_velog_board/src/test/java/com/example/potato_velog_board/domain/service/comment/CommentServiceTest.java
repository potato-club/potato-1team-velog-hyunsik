package com.example.potato_velog_board.domain.service.comment;

import com.example.potato_velog_board.domain.entity.board.Comment;
import com.example.potato_velog_board.domain.service.board.BoardService;
import com.example.potato_velog_board.domain.service.utils.TestServiceUtils;
import com.example.potato_velog_board.web.dto.request.board.BoardRequest;
import com.example.potato_velog_board.web.dto.request.board.CommentRequest;
import com.example.potato_velog_board.web.dto.response.user.UserFeignResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired
    CommentService commentService;
    @Autowired
    BoardService boardService;
    @Autowired
    EntityManager em;


    @BeforeEach
    public void init() {
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        String uuid = "123";
        boardService.createBoard(boardRequest, uuid);
    }

    @AfterEach
    public void clear() {
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("답글 작성하기 성공 ")
    void writeComment()  {
        //given
        Long boardId = 1L;
        final LocalDateTime date = LocalDateTime.of(2021, 9, 2, 22, 27, 50);
        final CommentRequest request = new CommentRequest("좋은 글이네요");
        final UserFeignResponse user = new UserFeignResponse("123", "hyun6ik", "amazonServer/uuid/img.png", date);
        //when
        final Comment comment = commentService.writeComment(request, user, 1L);
        //then
        assertThat(comment.getBoard().getId()).isEqualTo(1L);
        assertThat(comment.getContent()).isEqualTo("좋은 글이네요");
        assertThat(comment.getNickName()).isEqualTo("hyun6ik");

    }

}