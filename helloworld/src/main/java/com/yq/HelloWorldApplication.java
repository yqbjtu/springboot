package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class HelloWorldApplication {

	public static void main(String[] args) {
		String str = LocalDateTime.now().toString();
		System.out.println("str:" +str );
		SpringApplication.run(HelloWorldApplication.class, args);
		log.info("HelloWorldApplication Start done.");
	}
}
