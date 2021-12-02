package com.hw.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients("com.hw.example")
@ComponentScan("com.hw.example")
@MapperScan(("com.hw.example.dao"))
public class WxIntegralServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxIntegralServiceApplication.class, args);
	}

}
