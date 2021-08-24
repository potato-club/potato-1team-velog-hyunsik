package com.example.potato_velog_board.web.exceptionHandler;

import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.exception.S3FileException;
import com.example.potato_velog_board.utils.Result;
import com.example.potato_velog_board.utils.validation.ValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ValidationResult handleBindException(BindException bindException) {
        return ValidationResult.error(bindException, messageSource);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected Result<Object> handleResponseStatusException(ResponseStatusException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getRawStatusCode(), e.getMessage());
    }

    @ExceptionHandler(S3FileException.class)
    protected Result<Object> handleS3FileException(S3FileException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

}
