package com.fontalibros.spring_fontalibros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = DataSourceAutoConfiguration.class)
public class SpringFontalibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFontalibrosApplication.class, args);
	}

}
