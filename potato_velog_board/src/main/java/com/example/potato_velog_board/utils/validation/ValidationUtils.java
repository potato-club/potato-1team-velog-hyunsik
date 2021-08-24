package com.example.potato_velog_board.utils.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtils {


    public static void validateBindingResult(BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
    }

}
