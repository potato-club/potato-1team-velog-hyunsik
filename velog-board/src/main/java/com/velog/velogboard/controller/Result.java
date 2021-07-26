package com.velog.velogboard.controller;

import lombok.Getter;

@Getter
public class Result<T> {

    private int code;
    private T data;
    private String message;


    public Result(int code, T data){
        this.code = code;
        this.data = data;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static <T> Result<T> success(T data){
        return new Result(200, data);
    }

    public static Result<Object> error(int code, String message){
        return new Result(code,  message);
    }

    public static Result<Object> notFound(int code, String message){
        return new Result(code, message);
    }

}

