package com.dhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhs.entity.Planet;
import com.dhs.service.PlanetService;

@RestController
public class PlanetController {

	@Autowired
	PlanetService planetService;
	
	@PostMapping(value="/Planet/Save",consumes="application/json")
	public String saveUser(@RequestBody List<Planet> planets) {
		return planetService.savePlanet(planets);
	}
	
	@GetMapping(value = "/Planet/List", produces = "application/json")
	public Iterable<Planet> listProducts(){
		return planetService.getPlanetList();
	}

}
