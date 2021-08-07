package com.velog.velogboard.web.exceptionHandler;

import com.velog.velogcommon.utils.Result;
import com.velog.velogcommon.utils.validation.ValidationResult;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
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
        return Result.error(BAD_REQUEST.value(), e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected Result<Object> handleResponseStatusException(ResponseStatusException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getRawStatusCode(), e.getMessage());
    }

}
