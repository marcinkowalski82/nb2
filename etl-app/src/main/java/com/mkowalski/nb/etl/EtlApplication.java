package com.mkowalski.nb.etl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.mkowalski.nb.etl" })
@EnableAutoConfiguration
public class EtlApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlApplication.class, args);
	}

}
