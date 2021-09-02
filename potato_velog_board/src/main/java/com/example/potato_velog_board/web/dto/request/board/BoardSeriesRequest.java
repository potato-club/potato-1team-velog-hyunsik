package com.example.potato_velog_board.web.dto.request.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class BoardSeriesRequest {

    @Size(min = 2, max = 20)
    private String name;

    public BoardSeriesRequest(@Size(min = 2, max = 20) String name) {
        this.name = name;
    }
}
