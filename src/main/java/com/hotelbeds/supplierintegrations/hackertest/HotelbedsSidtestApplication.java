package com.hotelbeds.supplierintegrations.hackertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hotelbeds.supplierintegrations.hackertest.login.SecurityConfig;

@Configuration
@Import({ SecurityConfig.class })
@SpringBootApplication
@EnableScheduling
public class HotelbedsSidtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelbedsSidtestApplication.class, args);
	}

}
