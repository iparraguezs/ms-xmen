package com.meli.xmen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.meli.xmen")
public class MsXMenApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsXMenApplication.class, args);
	}
}