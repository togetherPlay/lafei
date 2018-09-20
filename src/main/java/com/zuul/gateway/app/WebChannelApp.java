package com.zuul.gateway.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.zuul.gateway"})
public class WebChannelApp {
	
	public static void main(String[] args) {
		SpringApplication.run(WebChannelApp.class, args);
	}
}
