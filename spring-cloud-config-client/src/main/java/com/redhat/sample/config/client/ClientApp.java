package com.redhat.sample.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.redhat.sample.config.controller.TestController;

@SpringBootApplication
@ComponentScan(basePackageClasses=TestController.class)
@ImportResource("classpath:spring/spring-cache-context.xml")
public class ClientApp {
	public static void main(String[] args) {
		// new SpringApplicationBuilder(ClientApp.class).web(true).run(args);
		SpringApplication.run(ClientApp.class, args);
	}
}
