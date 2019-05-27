package com.dhs.service;

import java.util.List;

import com.dhs.entity.Planet;
import com.dhs.entity.Route;
import com.dhs.entity.RouteResponse;

public interface PlanetService {
	Planet findById(Long id);
	Planet findByPlanetName(String planetName);
	Iterable<Planet> getPlanetList();
	String savePlanets(List<Planet> planets);
	String saveRoutes(List<Route> routes);
	Iterable<Route> getPlanetRouteList(String planetCode);
	Iterable<Route> getRouteList();
	RouteResponse getShortestPath(String planetSource, String planetDestination);
	int deletePlanet(Long planetCode);
}
