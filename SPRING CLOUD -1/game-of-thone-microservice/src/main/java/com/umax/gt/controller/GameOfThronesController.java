package com.umax.gt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/gameOfThrones/characters")
public class GameOfThronesController {
	private static final Logger log = LoggerFactory.getLogger(GameOfThronesController.class);
	private Faker faker=new Faker();
	private List<String> arreglo=new ArrayList<>();
	
	@PostConstruct
	public void init() {
		log.info("Game Of Thrones--log");
		for(int i=0; i<20;i++) {
			arreglo.add(faker.gameOfThrones().character());
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getCharacters(){
		return ResponseEntity.ok(arreglo);
	}
	

}
