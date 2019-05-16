package com.dhs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dhs.dao.PlanetRepository;
import com.dhs.entity.Planet;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	PlanetRepository planetReporsitory;
	
	@Override
	public Planet findByPlanetName(String planetName) {
		return planetReporsitory.findByPlanetName(planetName);
	}

	@Override
	public List<Planet> getPlanetList() {
		List<Planet> planates = new ArrayList<>();
		planetReporsitory.findAll().forEach(planates::add);
		return planates;
	}

	
	@Override
	public String savePlanet(List<Planet> planets){
		return planetReporsitory.saveAll(planets) + " record saved..";
	}

}
