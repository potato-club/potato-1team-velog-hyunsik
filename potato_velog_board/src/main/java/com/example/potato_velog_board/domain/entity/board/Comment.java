package com.example.potato_velog_board.domain.entity.board;

import com.example.potato_velog_board.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private String nickName;

    private String thumbnail;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(String uuid, String nickName, String thumbnail, String content) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.thumbnail = thumbnail;
        this.content = content;
    }

// 연관 관계 메소드

    public void addBoard(Board board) {
        this.board = board;
    }

    public static Comment of(String uuid, String nickName, String thumbnail, String content) {
        return new Comment(uuid, nickName, thumbnail, content);
    }
}
