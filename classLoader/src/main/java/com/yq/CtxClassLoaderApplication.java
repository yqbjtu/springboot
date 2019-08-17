package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CtxClassLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtxClassLoaderApplication.class, args);
		log.info("CtxClassLoaderApplication Start done.");
	}
}
