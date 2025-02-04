package com.compass.Desafio2_MicroserviceB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Desafio2MicroserviceBApplication {

	public static void main(String[] args) {
		SpringApplication.run(Desafio2MicroserviceBApplication.class, args);
	}

}
