package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.web.repository"})
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true) // <--
@EntityScan(basePackages = "com.web.*")
//@EnableTransactionManagement
//@EnableScheduling
public class BcShopApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BcShopApiApplication.class, args);
	}

}
