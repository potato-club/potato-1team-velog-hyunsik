package com.example.potato_velog_board.domain.entity;

import com.example.potato_velog_board.utils.BaseTimeEntity;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

    private String uuid;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "boardSeries_id")
    private BoardSeries boardSeries;

    @OneToOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "boardInfo_id")
    private BoardInfo boardInfo;

    @OneToMany(mappedBy = "board", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = ALL, orphanRemoval = true)
    private List<HashTag> hashTagList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = ALL, orphanRemoval = true)
    private List<BoardImage> boardImageList = new ArrayList<>();


    @Builder
    public Board(String title, String content, String uuid) {
        this.title = title;
        this.content = content;
        this.uuid = uuid;
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

    public static Board createBoard(BoardRequest request, String uuid) {
        return new Board().builder()
                .title(request.getTitle())
                .content(request.getContent())
                .uuid(uuid)
                .build();
    }





    public static Board of(Board board, BoardSeries boardSeries, BoardInfo boardInfo, List<HashTag> hashTagList,
                           List<BoardImage> boardImageList) {
        if(StringUtils.hasText(boardSeries.getName())){
            board.addBoardSeries(boardSeries);
        }
        board.addBoardInfo(boardInfo);
        for (HashTag hashTag : hashTagList) {
            board.addHashTag(hashTag);
        }
        for (BoardImage boardImage : boardImageList) {
            board.addBoardImage(boardImage);
        }
        return board;
    }


}
