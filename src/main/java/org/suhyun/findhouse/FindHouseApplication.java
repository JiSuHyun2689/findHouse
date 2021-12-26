package org.suhyun.findhouse;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FindHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FindHouseApplication.class, args);
    }

}
