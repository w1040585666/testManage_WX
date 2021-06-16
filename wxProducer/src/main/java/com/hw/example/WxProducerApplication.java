package com.hw.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.hw.example")
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class WxProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxProducerApplication.class, args);
	}
}
