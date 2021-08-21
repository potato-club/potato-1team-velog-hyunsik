package com.velog.velogcommon.board.entity;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    public HashTag(String name) {
        this.name = name;
    }

    // 연관 관계 메소드
    public void addBoard(Board board) {
        this.board =board;
    }


    public static List<HashTag> createHashTagList(BoardRequest request) {
       return request.getHashTagRequestList().stream().map(i -> new HashTag(i.getName())).collect(Collectors.toList());
    }

}
