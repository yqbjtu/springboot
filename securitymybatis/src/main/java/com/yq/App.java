package com.yq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @SpringBootApplication指定这是一个 spring boot的应用程序.
@SpringBootApplication
// 扫描数据访问层接口的包名。
@MapperScan("com.yq.mapper")
public class App {
	public static void main(String[] args) {
		// SpringApplication 用于从main方法启动Spring应用的类。
		SpringApplication.run(App.class, args);
		String pw1 = new BCryptPasswordEncoder().encode("admin");
		String pw2 = new BCryptPasswordEncoder().encode("user1pw");
		System.out.println(pw1);
		System.out.println(pw2);
	}
}
