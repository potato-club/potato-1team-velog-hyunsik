package com.velog.velogcommon.utils.validation;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ValidationCode {

    @Value("${NotBlank.email}")
    private String NotBlankEmail;
    @Value("${NotBlank.password}")
    private String NotBlankPassword;
    @Value("${NotBlank.name}")
    private String NotBlankName;
    @Value("${NotBlank.nickName}")
    private String NotBlankNickName;
    @Value("${Size.email}")
    private String SizeEmail;
    @Value("${Size.password}")
    private String SizePassword;
    @Value("${Size.name}")
    private String SizeName;
    @Value("${Size.nickName}")
    private String SizeNickName;
    @Value("${Size.introduce}")
    private String SizeIntroduce;
}
