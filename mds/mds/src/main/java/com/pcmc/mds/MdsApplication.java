package com.pcmc.mds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdsApplication.class, args);
	}

}
