package com.umax.client.clients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("Lsoto-dragon-ball")
@LoadBalancerClient(name = "Lsoto-dragon-ball",configuration=LsotoLoadBalancerConfiguration.class)
public interface DragonBallCharacterClient {
	
	@RequestMapping(method = RequestMethod.GET,value = "/application-name")
	public ResponseEntity<String> getApplication();

} 
