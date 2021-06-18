package com.hw.example;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEurekaClient
@EnableTransactionManagement
@ComponentScan("com.hw.example")
@MapperScan(("com.hw.example.dao"))
public class WxIntegralServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxIntegralServiceApplication.class, args);
	}

}
