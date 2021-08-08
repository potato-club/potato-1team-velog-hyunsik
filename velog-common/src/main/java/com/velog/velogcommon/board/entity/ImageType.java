package com.velog.velogcommon.board.entity;

import lombok.Getter;

@Getter
public enum ImageType {

    CONTENT("게시글 사진"),
    THUMBNAIL("썸네일");


    private String description;

    ImageType(String description) {
        this.description = description;
    }
}
