package com.dhs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhs.dao.PlanetRepository;
import com.dhs.dao.RouteRepository;
import com.dhs.entity.Planet;
import com.dhs.entity.Route;
import com.dhs.entity.RouteResponse;
import com.dhs.utility.Dijkstras_Shortest_Path;
import com.dhs.utility.Vertex;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	PlanetRepository planetReporsitory;
	
	@Autowired
	RouteRepository routeReporsitory;
	
	
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
	public Iterable<Route> getRouteList() {
		List<Route> routelist = new ArrayList<>();
		routeReporsitory.findAll().forEach(routelist::add);
		return routelist;
	}

	@Override
	public String savePlanets(List<Planet> planets){
		return planetReporsitory.saveAll(planets) + " record saved..";
	}
	
	@Override
	public String saveRoutes(List<Route> routes){
		System.out.println(routes);
		return routeReporsitory.saveAll(routes) + " record saved..";
	}
	
	@Override
	public List<Route> getPlanetRouteList(String planetNode) {
		List<Route> routeList = new ArrayList<>();
		routeReporsitory.findByPlanetOrigin(planetNode).forEach(routeList::add);
		return routeList;
	}
	
	@Override
	public RouteResponse getShortestPath(String planetOrigin, String planetDestination) {
		HashMap<String,Object> details = new HashMap<>();
		double distance = Dijkstras_Shortest_Path.computePaths(planetOrigin,planetDestination); // run Dijkstra
        List<Route> routes = Dijkstras_Shortest_Path.getShortestPathTo(planetDestination);
        RouteResponse routeResponse = new RouteResponse();
        routeResponse.setPlanetOrigin(planetOrigin);
        routeResponse.setPlanetDestination(planetDestination);
        routeResponse.setDistance(distance);
        routeResponse.setListRoutes(routes);
        System.out.println(routeResponse);
		return routeResponse;
	}

}
