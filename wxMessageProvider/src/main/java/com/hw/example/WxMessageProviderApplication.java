package com.hw.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WxMessageProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxMessageProviderApplication.class, args);
	}

}
