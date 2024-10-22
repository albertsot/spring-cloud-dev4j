package com.umax.client;

import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.umax.client.clients.DragonBallCharacterClient;

@SpringBootApplication
@EnableFeignClients
public class DragonBallClientStandaloneApplication implements ApplicationRunner{
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DragonBallCharacterClient dragonBallCharacterClient;
	
	private static final Logger log = LoggerFactory.getLogger(DragonBallClientStandaloneApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(DragonBallClientStandaloneApplication.class, args);
	} 

	@Override
	public void run(ApplicationArguments args) throws Exception {
	for(int i=0;i<10;i++) {
		ResponseEntity<String> responseEntity = dragonBallCharacterClient.getApplication();
		log.info("status {}",responseEntity.getStatusCode());
		String body = responseEntity.getBody();
		log.info("Body {}",body);
	} 
		
	}

	/*@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		Application application = eurekaClient.getApplication("Lsoto-dragon-ball");
		log.info("Application name {}",application.getName());
		List<InstanceInfo> instances = application.getInstances();
		for (InstanceInfo instanceInfo : instances) {
			log.info("Ip address {}:{}",instanceInfo.getIPAddr(),instanceInfo.getPort());
		}
		
	}*/

}
