package com.example.potato_velog_user.exceptionHandler;

import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.exception.BusinessException;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.exception.NotMatchException;
import com.example.potato_velog_user.utils.Result;
import com.example.potato_velog_user.utils.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

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
        return Result.error(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleAlreadyExistException(AlreadyExistException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    protected Result<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NotMatchException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleNotMatchException(NotMatchException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ValidationResult handleBindException(BindException bindException) {
        return ValidationResult.error(bindException, messageSource);
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected Result<Object> handleResponseStatusException(ResponseStatusException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getRawStatusCode(), e.getMessage());
    }
}
