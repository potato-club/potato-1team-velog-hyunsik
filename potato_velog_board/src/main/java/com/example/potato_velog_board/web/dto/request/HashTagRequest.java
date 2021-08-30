package com.example.potato_velog_board.web.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashTagRequest {

    private String name;

    public HashTagRequest(String name) {
        this.name = name;
    }
}
