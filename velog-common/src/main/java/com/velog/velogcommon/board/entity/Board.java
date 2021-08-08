package com.velog.velogcommon.board.entity;

import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "boardSeries_id")
    private BoardSeries boardSeries;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "boardInfo_id")
    private BoardInfo boardInfo;

    @OneToMany(mappedBy = "board", cascade = ALL)
    private final List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = ALL)
    private final List<HashTag> hashTagList = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
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

    public static Board of(Board board, BoardSeries boardSeries, BoardInfo boardInfo, List<Comment> commentList, List<HashTag> hashTagList) {
        board.addBoardSeries(boardSeries);
        board.addBoardInfo(boardInfo);
        for (Comment comment : commentList) {
            board.addComment(comment);
        }
        for (HashTag hashTag : hashTagList) {
            board.addHashTag(hashTag);
        }
        return board;
    }

    public void addUser(User user) {
        this.user = user;
    }
}
