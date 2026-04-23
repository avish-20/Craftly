package com.avish.Craftly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraftlyApplication {

	public static void main(String[] args) {
		// Set timezone to UTC to avoid PostgreSQL timezone issues
		System.setProperty("user.timezone", "UTC");
		SpringApplication.run(CraftlyApplication.class, args);
	}

}
