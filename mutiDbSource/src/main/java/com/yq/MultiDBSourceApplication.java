package com.yq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.yq.mapper")
public class MultiDBSourceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MultiDBSourceApplication.class, args);
	}
}
