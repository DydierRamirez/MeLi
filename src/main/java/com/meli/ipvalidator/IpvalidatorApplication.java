package com.meli.ipvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.meli.ipvalidator.*"})
@RefreshScope
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
public class IpvalidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpvalidatorApplication.class, args);
	}

}
