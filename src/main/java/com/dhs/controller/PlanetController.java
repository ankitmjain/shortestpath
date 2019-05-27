package com.dhs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity savePlanets(@RequestBody List<Planet> planets) {
		return ResponseEntity.ok(planetService.savePlanets(planets));
	}
	
	@PostMapping(value="/Route/Save",consumes="application/json")
	public ResponseEntity saveRoutes(@RequestBody List<Route> routes) {
		return ResponseEntity.ok(planetService.saveRoutes(routes));
	}
	
	@GetMapping(value = "/Planet/List", produces = "application/json")
	public ResponseEntity<Iterable<Planet>> listPlanets(){
		return ResponseEntity.ok(planetService.getPlanetList());
	}
	
	@GetMapping(value = "/Route/List", produces = "application/json")
	public ResponseEntity<Iterable<Route>> listRoutes(){
		return ResponseEntity.ok(planetService.getRouteList());
	}
	
	@GetMapping(value = "/Planet/{planetCode}/Route", produces = "application/json")
	public ResponseEntity<Iterable<Route>> listRouteBaseonPlanet(@PathVariable String planetCode){
		return ResponseEntity.ok(planetService.getPlanetRouteList(planetCode));
	}
	
	@DeleteMapping("/Planet/{planetCode}/Delete")
    public ResponseEntity deletePlanet(@PathVariable Long planetCode){
		if (planetService.findById(planetCode)==null) {
            ResponseEntity.badRequest().build();
        }
		planetService.deletePlanet(planetCode);

        return ResponseEntity.ok().build();
		
	}
	
	@GetMapping(value = "/ShortestPath/{planetSource}/{planetDestination}", produces = "application/json")
	public ResponseEntity<RouteResponse> getShortestPath(@PathVariable String planetSource,@PathVariable String planetDestination){
		return ResponseEntity.ok(planetService.getShortestPath(planetSource,planetDestination));
	}
	
	

}
