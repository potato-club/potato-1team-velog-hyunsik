package com.example.potato_velog_board.web.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSeriesRequest {

    @Size(min = 2, max = 20)
    private String name;
}
