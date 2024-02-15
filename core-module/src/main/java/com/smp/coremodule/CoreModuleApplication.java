package com.smp.coremodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CoreModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreModuleApplication.class, args);
	}

}
