package com.velog.velogboard.web.exceptionHandler;

import com.velog.velogboard.domain.utils.Result;
import com.velog.velogboard.exception.JwtTokenException;
import com.velog.velogboard.validation.ValidationResult;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@Slf4j
@RequiredArgsConstructor
public class customControllerAdvice {

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
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(JwtTokenException.class)
    @ResponseStatus(BAD_REQUEST)
    protected Result<Object> handleJwtTokenExceptionException(JwtTokenException e) {
        log.error(e.getMessage(), e);
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }
}
