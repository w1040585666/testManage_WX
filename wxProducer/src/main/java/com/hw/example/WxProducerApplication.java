package com.hw.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.hw.example")

public class WxProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxProducerApplication.class, args);
	}
}
