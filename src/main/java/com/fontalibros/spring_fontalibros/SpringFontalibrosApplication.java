package com.fontalibros.spring_fontalibros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class SpringFontalibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFontalibrosApplication.class, args);
	}

}
