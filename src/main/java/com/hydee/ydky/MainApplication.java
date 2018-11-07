package com.hydee.ydky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportResource("classpath:transaction.xml")
@MapperScan("com.hydee.ydky.dao")
@EnableScheduling
public class MainApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
	
}
