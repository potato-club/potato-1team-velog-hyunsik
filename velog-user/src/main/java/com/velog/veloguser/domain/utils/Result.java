package com.velog.veloguser.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<T> {

    private int code;
    private T data;
    private String message;


    public static <T> Result<T> success(T data) {
        return new Result(200, data, null);
    }

    public static Result<Object> error(int code, String message) {
        return new Result(code, null, message);
    }

    public static Result<Object> notFound(int code, String message) {
        return new Result(code, null, message);
    }

}
