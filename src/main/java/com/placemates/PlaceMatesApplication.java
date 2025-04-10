package com.placemates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PlaceMatesApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaceMatesApplication.class, args);
	}
}
