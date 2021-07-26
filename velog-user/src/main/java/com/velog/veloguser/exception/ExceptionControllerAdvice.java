package com.velog.veloguser.exception;

import com.velog.veloguser.domain.utils.Result;
import com.velog.veloguser.validator.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleCustomException(BusinessException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }


    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleAlreadyExistException(AlreadyExistException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ValidationResult handleBindException(BindException bindException){
        return ValidationResult.error(bindException, messageSource);
    }


}
