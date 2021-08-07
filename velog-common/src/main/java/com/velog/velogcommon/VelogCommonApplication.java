package com.velog.velogcommon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class VelogCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelogCommonApplication.class, args);
    }


}
