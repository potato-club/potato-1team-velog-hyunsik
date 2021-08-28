package com.example.potato_velog_board.domain.entity;

import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String introduce;

    private int likeCount;

    private boolean isPublic;

    private String boardUrl;

    @OneToOne(mappedBy = "boardInfo", fetch = LAZY)
    private Board board;

    @Builder
    public BoardInfo(String introduce, int likeCount, boolean isPublic, String boardUrl) {
        this.introduce = introduce;
        this.likeCount = likeCount;
        this.isPublic = isPublic;
        this.boardUrl = boardUrl;
    }

    public static BoardInfo of(BoardRequest request) {
        return new BoardInfo().builder()
                .introduce(request.getBoardInfoRequest().getIntroduce())
                .likeCount(0)
                .isPublic(request.getBoardInfoRequest().isPublic())
                .boardUrl(request.getBoardInfoRequest().getBoardUrl())
                .build();
    }


    //
    public void addBoard(Board board) {
        this.board = board;
    }
}
