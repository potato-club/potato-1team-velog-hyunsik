package com.velog.velogboard.web.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 400:
                if (methodKey.contains("validateToken")) {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "토큰 검증 과정에서 400 에러 발생!!");
                }
                return new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
            case 404:
                if (methodKey.contains("validateToken")) {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "토큰 검증 과정에서 잘못된 경로로 접근");
                }
                return new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 경로를 찾을 수 없습니다..");
            case 500:
                if (methodKey.contains("validateToken")) {
                    return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 검증 과정에서 서버 에러 발생!!");
                }
                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러입니다.");
            default:
                return new Exception(response.reason());
        }
    }
}
