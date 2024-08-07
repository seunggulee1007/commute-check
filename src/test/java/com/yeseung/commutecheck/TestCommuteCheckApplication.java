package com.yeseung.commutecheck;

import org.springframework.boot.SpringApplication;

public class TestCommuteCheckApplication {

    public static void main(String[] args) {
        SpringApplication.from(CommuteCheckApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
