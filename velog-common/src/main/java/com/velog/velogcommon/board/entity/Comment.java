package com.velog.velogcommon.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    // 연관 관계 메소드

    public void addBoard(Board board) {
        this.board = board;
    }
}
