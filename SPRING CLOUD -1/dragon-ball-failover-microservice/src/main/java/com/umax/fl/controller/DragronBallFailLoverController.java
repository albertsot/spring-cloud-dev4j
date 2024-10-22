package com.umax.fl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

@RestController
@RequestMapping("/api/v1/failover/characters")
public class DragronBallFailLoverController {
	
	private Faker faker=new Faker();
	private List<String> arreglo=new ArrayList<>();
	
	@PostConstruct
	public void init() {
		for(int i=0;i<20;i++) {
			arreglo.add(String.format("Failover - %s", faker.name().fullName()));
			
		}
	}
	
	@GetMapping
	public ResponseEntity<List<String>> getCharacters(){
		return ResponseEntity.ok(arreglo);
	}
	

}
