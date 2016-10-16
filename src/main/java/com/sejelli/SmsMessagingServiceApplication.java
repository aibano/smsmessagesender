package com.sejelli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmsMessagingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsMessagingServiceApplication.class, args);
	}
}
