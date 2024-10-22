package com.umax.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DragonBallMicroservice1Application {

	public static void main(String[] args) {
		SpringApplication.run(DragonBallMicroservice1Application.class, args);
	}

}
