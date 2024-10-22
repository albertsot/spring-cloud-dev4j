package com.umax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.umax.service.FooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/v1/dragonball/characters")
public class DragonBallController {
	private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);
	private Faker faker=new Faker();

	@Autowired
	private FooService fooService;
	private List<String> arreglo=new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0;i<20;i++) {
			arreglo.add(faker.name().fullName());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getCharacters(){
		log.info("Obteniendo caractetisticas de Dragon ball");
		fooService.printLog();
		return ResponseEntity.ok(arreglo);
	}
	

}
