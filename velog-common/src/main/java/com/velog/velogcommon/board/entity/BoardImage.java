package com.velog.velogcommon.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
}
