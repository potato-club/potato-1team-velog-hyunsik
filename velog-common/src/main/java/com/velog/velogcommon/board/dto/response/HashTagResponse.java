package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTagResponse {

    private String name;

    @Builder
    public HashTagResponse(String name) {
        this.name = name;
    }

    public static List<HashTagResponse> of(Board board) {
        return board.getHashTagList().stream().map(i -> new HashTagResponse().builder()
                .name(i.getName()).build())
                .collect(Collectors.toList());
    }
}
