package com.vijay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HostelProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostelProjectApplication.class, args);
	}

}
