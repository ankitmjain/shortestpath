package com.dhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhs.entity.Planet;
import com.dhs.entity.Route;
import com.dhs.entity.RouteResponse;
import com.dhs.service.PlanetService;

@RestController
@RequestMapping("/api/v1/")
public class PlanetController {

	@Autowired
	PlanetService planetService;
	
	@PostMapping(value="/Planet/Save",consumes="application/json")
	public String savePlanets(@RequestBody List<Planet> planets) {
		return planetService.savePlanets(planets);
	}
	
	@PostMapping(value="/Route/Save",consumes="application/json")
	public String saveRoutes(@RequestBody List<Route> routes) {
		return planetService.saveRoutes(routes);
	}
	
	@GetMapping(value = "/Planet/List", produces = "application/json")
	public Iterable<Planet> listPlanets(){
		return planetService.getPlanetList();
	}
	
	@GetMapping(value = "/Route/List", produces = "application/json")
	public Iterable<Route> listRoutes(){
		return planetService.getRouteList();
	}
	
	@GetMapping(value = "/Planet/{planetCode}/Route", produces = "application/json")
	public Iterable<Route> listRouteBaseonPlanet(@PathVariable String planetCode){
		return planetService.getPlanetRouteList(planetCode);
	}
	@GetMapping(value = "/ShortestPath/{planetSource}/{planetDestination}", produces = "application/json")
	public RouteResponse getShortestPath(@PathVariable String planetSource,@PathVariable String planetDestination){
		return planetService.getShortestPath(planetSource,planetDestination);
	}
	
	

}
