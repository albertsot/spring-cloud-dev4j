package com.umax.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umax.config.DragonBallConfig;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);

	
	@Autowired
	private DragonBallConfig dragonballConfig;
	
	@GetMapping
	public ResponseEntity<String> getAppName(){
		log.info("Gettint application name");
		return ResponseEntity.ok(dragonballConfig.getApplicationName());
	}

}
