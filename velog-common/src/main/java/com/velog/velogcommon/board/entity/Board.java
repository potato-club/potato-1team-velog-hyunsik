package com.velog.velogcommon.board.entity;

import com.velog.velogcommon.utils.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    private String userId;

    @Builder
    public Board(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public static Board of(String title, String content, String userId) {
        return new Board().builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
