package com.resende.eventsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EventsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsMicroserviceApplication.class, args);
	}

}
