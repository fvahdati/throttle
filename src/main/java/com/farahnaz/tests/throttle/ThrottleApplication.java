package com.farahnaz.tests.throttle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.farahnaz.tests.throttle")
@SpringBootApplication
public class ThrottleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThrottleApplication.class, args);
	}
}
