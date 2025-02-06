package com.compass.Desafio2_MicroserviceA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Desafio2MicroserviceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(Desafio2MicroserviceAApplication.class, args);
	}

}
