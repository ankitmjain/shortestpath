package com.dhs.service;

import java.util.List;

import com.dhs.entity.Planet;

public interface PlanetService {
	Planet findByPlanetName(String planetName);
	Iterable<Planet> getPlanetList();
	String savePlanet(List<Planet> planets);
}
