package com.velog.veloguser.validator;

import lombok.*;
import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldErrorDetail {

    private final String objectName;
    private final String field;
    private final String code;
    private final String message;


    public static FieldErrorDetail of(FieldError fieldError, MessageSource messageSource){
        return new FieldErrorDetail(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getCode(),
                messageSource.getMessage(fieldError, Locale.KOREA)
        );
    }


}
