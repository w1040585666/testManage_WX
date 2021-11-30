package com.hw.example;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.hw.example.init.InitSystemFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan("com.hw.example")
@EnableHystrix
@NacosPropertySource(dataId = "config-properties", autoRefreshed = true)
public class WxUserServiceApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	InitSystemFunction initSystemFunction;

	public static void main(String[] args) {
		SpringApplication.run(WxUserServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WxUserServiceApplication.class);
	}

	@Override
	public void run(String... strings) {
		initSystemFunction.initFunction();
	}
}
