package com.velog.veloguser.web.error;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//public class FeignErrorDecoder implements ErrorDecoder {

//    @Override
//    public Exception decode(String methodKey, Response response) {
//
//        switch (response.status()) {
//            case 400:
//                return new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 접근입니다.");
//            case 404:
//                if (methodKey.contains("myBoardList")) {
//                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "나의 게시글 리스트들을 가져오는 도중 오류가 생겼습니다.");
//                }
//                return new ResponseStatusException(HttpStatus.NOT_FOUND, "경로를 찾을 수 업습니다.");
//            case 500:
//                return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러 발생!!");
//            default:
//                return new ResponseStatusException(HttpStatus.BAD_REQUEST, response.reason());
//        }
//
//    }
//}
