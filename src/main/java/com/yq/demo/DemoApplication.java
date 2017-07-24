package com.yq.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableAutoConfiguration
@RestController

public class DemoApplication {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World2";
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
