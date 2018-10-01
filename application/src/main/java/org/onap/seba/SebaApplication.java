package org.onap.seba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class SebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SebaApplication.class, args);
	}
}
