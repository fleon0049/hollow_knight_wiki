package com.hollow_knight.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/** Cliente HTTP que usa PokeApiClient para comunicarse con PokeAPI. */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
