package com.velog.velogauth.exceptionHandler;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.exception.JwtTokenException;
import com.velog.velogcommon.exception.NotMatchException;
import com.velog.velogcommon.utils.Result;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ExceptionControllerAdvice {


    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleAlreadyExistException(AlreadyExistException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(NotMatchException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleNotMatchException(NotMatchException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(JwtTokenException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleJwtTokenExceptionException(JwtTokenException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

}
