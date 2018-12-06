package com.ipacs.qywx.pls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@MapperScan(basePackages = "com.ipacs.qbook.dao")
@ComponentScan(basePackages = {"com.ipacs.qbook"})
@SpringBootApplication
public class PlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlsApplication.class, args);
	}
}
