package com.ylluberes.dofus_collector_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class DofusCollectorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DofusCollectorApiApplication.class, args);
	}

}
