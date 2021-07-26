package com.velog.velogboard.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    private String content;

    @Column(nullable = false)
    private String userId;

    private int likesCount;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private final List<BoardLike> boardLikeList = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, String userId, int likesCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.likesCount = likesCount;
    }

}
