package com.example.potato_velog_board.domain.entity;

import com.example.potato_velog_board.utils.BaseTimeEntity;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String content;

    private Long userUUId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "boardSeries_id")
    private BoardSeries boardSeries;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "boardInfo_id")
    private BoardInfo boardInfo;

    @OneToMany(mappedBy = "board", cascade = ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = ALL)
    private List<HashTag> hashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = ALL)
    private List<BoardImage> boardImageList = new ArrayList<>();


    @Builder
    public Board(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userUUId = userId;
    }

    //연관 관계 메소드
    public void addBoardSeries(BoardSeries boardSeries) {
        this.boardSeries = boardSeries;
        boardSeries.getBoardList().add(this);
    }

    public void addBoardInfo(BoardInfo boardInfo) {
        this.boardInfo= boardInfo;
        boardInfo.addBoard(this);
    }

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.addBoard(this);
    }

    public void addHashTag(HashTag hashTag) {
        hashTagList.add(hashTag);
        hashTag.addBoard(this);
    }

    public void addBoardImage(BoardImage boardImage) {
        boardImageList.add(boardImage);
        boardImage.addBoard(this);
    }

    public static Board createBoard(BoardRequest request, Long userId) {
        return new Board().builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(userId)
                .build();
    }


    public static Board of(Board board, BoardSeries boardSeries, BoardInfo boardInfo, List<Comment> commentList, List<HashTag> hashTagList,
                           List<BoardImage> boardImageList) {
        board.addBoardSeries(boardSeries);
        board.addBoardInfo(boardInfo);
        for (Comment comment : commentList) {
            board.addComment(comment);
        }
        for (HashTag hashTag : hashTagList) {
            board.addHashTag(hashTag);
        }
        for (BoardImage boardImage : boardImageList) {
            board.addBoardImage(boardImage);
        }
        return board;
    }


}
