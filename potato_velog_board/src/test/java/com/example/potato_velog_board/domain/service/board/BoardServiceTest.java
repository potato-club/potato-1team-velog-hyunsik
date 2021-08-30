package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.config.StorageConfig;
import com.example.potato_velog_board.domain.entity.Board;
import com.example.potato_velog_board.domain.entity.ImageType;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.request.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application.yml")
class BoardServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    public void init() {
        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("게시판 게시글 생성 성공")
    void createBoard() {

        //given
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();

        String uuid = "123";
        //when
        final Board saveBoard = boardService.createBoard(boardRequest, uuid);
        //then
        assertThat(saveBoard.getTitle()).isEqualTo("게시글1");
        assertThat(saveBoard.getContent()).isEqualTo("내용");
        assertThat(saveBoard.getBoardSeries().getName()).isEqualTo("JAVA");
        assertThat(saveBoard.getBoardImageList().size()).isEqualTo(2);
        assertThat(saveBoard.getBoardImageList()).extracting("originalImageName")
                .containsExactly("test1.png", "test2.png");
        assertThat(saveBoard.getHashTagList().size()).isEqualTo(2);
        assertThat(saveBoard.getHashTagList()).extracting("name")
                .containsExactly("JAVA", "Spring");

    }


    @Test
    @DisplayName("게시판 게시글 수정 성공")
    void updateBoard() {
        //given
        String uuid = "123";
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        boardService.createBoard(boardRequest,uuid);

        final BoardRequest updateRequest = TestServiceUtils.updateBoardRequest();

        //when
        final Board updateBoard = boardService.updateBoard(updateRequest, 1L, uuid);
        //then
        assertThat(updateBoard.getId()).isEqualTo(1L);
        assertThat(updateBoard.getTitle()).isEqualTo("1번 게시글 수정");
        assertThat(updateBoard.getContent()).isEqualTo("수정된 내용");
        assertThat(updateBoard.getBoardSeries().getName()).isEqualTo("UPDATE");
        assertThat(updateBoard.getBoardSeries().getId()).isEqualTo(1L);
        assertThat(updateBoard.getBoardInfo().getIntroduce()).isEqualTo("자기소개 수정");
        assertThat(updateBoard.getBoardInfo().getId()).isEqualTo(1L);
        assertThat(updateBoard.getBoardImageList().size()).isEqualTo(1);
        assertThat(updateBoard.getBoardImageList()).extracting("originalImageName").containsExactly("updatetest1.png");
        assertThat(updateBoard.getHashTagList().size()).isEqualTo(3);
        assertThat(updateBoard.getHashTagList()).extracting("name")
                .containsExactly("UPDATE_JAVA", "UPDATE_Spring", "UPDATE_JPA");
    }

    @Test
    @DisplayName("게시판 게시글 수정 실패 - 해당 게시글 못찾았을 때")
    void updateBoard_fail() {
        //given
        String uuid = "123";
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        boardService.createBoard(boardRequest,uuid);
        final BoardRequest updateRequest = TestServiceUtils.updateBoardRequest();
        //then
        assertThatThrownBy(() -> boardService.updateBoard(updateRequest, 100L, uuid))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.NOT_FOUND_EXCEPTION_BOARD.getMessage());

    }

    @Test
    @DisplayName("게시판 게시글 삭제 성공")
    void deleteBoard() {
        //given
        String uuid = "123";
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        boardService.createBoard(boardRequest,uuid);
        //when
        final String result = boardService.deleteBoard(1L, "123");
        //then
        assertThat(result).isEqualTo("게시글 삭제 성공 id = 1");
    }

    @Test
    @DisplayName("게시판 게시글 삭제 실패 - 게시글 아이디가 다를때")
    void deleteBoard_fail() {
        //given
        String uuid = "123";
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        boardService.createBoard(boardRequest,uuid);
        //then
        assertThatThrownBy(() -> boardService.deleteBoard(100L, "123"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.NOT_FOUND_EXCEPTION_BOARD.getMessage());

    }

    @Test
    @DisplayName("게시판 게시글 삭제 실패 - 유저 아이디가 다를때")
    void deleteBoard_fail2() {
        //given
        String uuid = "12312321312";
        final BoardRequest boardRequest = TestServiceUtils.createBoardRequest();
        boardService.createBoard(boardRequest,uuid);
        //then
        assertThatThrownBy(() -> boardService.deleteBoard(1L, "123"))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.NOT_FOUND_EXCEPTION_BOARD.getMessage());


    }






}