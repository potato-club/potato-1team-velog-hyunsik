package com.velog.veloguser.validator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {

    private final List<FieldErrorDetail> errors;

    public static ValidationResult error(Errors errors, MessageSource messageSource) {
        return new ValidationResult(
                errors.getFieldErrors().stream().map(error -> FieldErrorDetail.of(error, messageSource)).collect(Collectors.toList())
        );

    }
}
