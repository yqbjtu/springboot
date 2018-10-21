package com.yq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanOrderApplication {
	private static final Logger logger = LoggerFactory.getLogger(BeanOrderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BeanOrderApplication.class, args);
		logger.info("BeanOrderApplication Start done.");
	}
}
