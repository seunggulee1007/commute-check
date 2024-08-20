package com.yeseung.commutecheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ConfigurationPropertiesScan(
    basePackages = {"com.yeseung.commutecheck.common.properties",
        "com.ulisesbocchio.jasyptspringboot.properties",
        "com.yeseung.commutecheck.modules.crawling.properties",
        "com.yeseung.commutecheck.modules.commute.properties"})
public class CommuteCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommuteCheckApplication.class, args);
    }

}
