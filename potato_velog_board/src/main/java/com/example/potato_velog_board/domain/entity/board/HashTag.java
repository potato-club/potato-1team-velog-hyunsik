package com.example.potato_velog_board.domain.entity.board;

import com.example.potato_velog_board.utils.BaseTimeEntity;
import com.example.potato_velog_board.web.dto.request.board.BoardRequest;
import com.example.potato_velog_board.web.dto.request.board.HashTagRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag extends BaseTimeEntity {

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


    public static List<HashTag> of(BoardRequest request) {
       return request.getHashTagRequestList().stream().map(i -> new HashTag(i.getName())).collect(Collectors.toList());
    }


    public void update(HashTagRequest hashTagRequest) {
        this.name = hashTagRequest.getName();
    }
}
