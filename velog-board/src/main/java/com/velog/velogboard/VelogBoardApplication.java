package com.velog.velogboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableJpaAuditing
@EntityScan(basePackages = {"com.velog.velogcommon"})
@EnableJpaRepositories(basePackages = {"com.velog.velogcommon"})
public class VelogBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelogBoardApplication.class, args);
    }

}
