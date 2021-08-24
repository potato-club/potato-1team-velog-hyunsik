package com.example.potato_velog_board.utils.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    NOT_FOUND_EXCEPTION_USER(404, "해당 하는 계정을 찾을 수 없습니다."),
    NOT_FOUND_EXCEPTION_BOARD(404,"해당 하는 게시글을 찾을 수 없습니다."),

    ALREADY_EXIST_EXCEPTION_USER(400, "이미 존재하는 계정입니다."),
    ALREADY_EXIST_EXCEPTION_NICKNAME(400, "이미 존재하는 별명입니다."),

    JWT_TOKEN_EXCEPTION(400, "토큰 오류 입니다."),
    JWT_TOKEN_EXCEPTION_PARSING(400, "토큰 파싱 오류 입니다."),
    JWT_TOKEN_EXCEPTION_INVALID(400,"유효한 토큰이 없습니다."),

    FILE_CONVERT_EXCEPTION(400, "MultipartFile을 File로 컨버팅 하는데 실패했습니다.");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
