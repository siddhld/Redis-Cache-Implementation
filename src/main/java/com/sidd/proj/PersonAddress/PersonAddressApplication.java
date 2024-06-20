package com.sidd.proj.PersonAddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PersonAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonAddressApplication.class, args);
	}

}
