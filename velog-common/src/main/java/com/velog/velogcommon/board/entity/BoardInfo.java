package com.velog.velogcommon.board.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

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


    //
    public void addBoard(Board board) {
        this.board = board;
    }
}
