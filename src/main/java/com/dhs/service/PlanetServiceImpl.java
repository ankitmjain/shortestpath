package com.dhs.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhs.dao.PlanetRepository;
import com.dhs.dao.RouteRepository;
import com.dhs.entity.Planet;
import com.dhs.entity.Route;
import com.dhs.entity.RouteResponse;
import com.dhs.utility.Dijkstra;
import com.dhs.utility.Graph;
import com.dhs.utility.Node;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	PlanetRepository planetReporsitory;
	
	@Autowired
	RouteRepository routeReporsitory;
	
	@Override
	public Planet findById(Long planetID) {
		return planetReporsitory.findByPlanetID(planetID);
	}
	
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
		List<Route> routeList = new ArrayList<>();
		routeReporsitory.findAll().forEach(routeList::add);
		return routeList;
	}

	@Override
	public String savePlanets(List<Planet> planets){
		return planetReporsitory.saveAll(planets) + " record saved..";
	}
	
	@Override
	public int deletePlanet(Long id) {
		planetReporsitory.deleteById(id);
		return 1;
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
		
		List<Route> routeList = new ArrayList<>();
		routeReporsitory.findAll().forEach(routeList::add);
		
		Map<String,Node> map = new LinkedHashMap<>();
		
		Graph graph = new Graph();
		routeList.forEach((temp)->{
			
			Node originMap = map.get(temp.getPlanetOrigin());
			Node destinationMap = map.get(temp.getPlanetDestination());
			if(originMap==null)
				originMap = new Node(temp.getPlanetOrigin());
			if(destinationMap==null)
				destinationMap = new Node(temp.getPlanetDestination());
			
			originMap.addDestination(destinationMap,temp.getDistance(),temp.getTrafficDelay());
			map.put(temp.getPlanetOrigin(),originMap);
			map.put(temp.getPlanetDestination(),destinationMap);
		});
		
		for (Entry<String,Node> node : map.entrySet()) {
        	 graph.addNode(node.getValue());
		}
		
		graph = Dijkstra.calculateShortestPathFromSource(graph,map.get(planetOrigin));
    	
		
		 RouteResponse routeResponse = new RouteResponse();
		 routeResponse.setPlanetOrigin(planetOrigin);
		 routeResponse.setPlanetDestination(planetDestination);
    	
		 (graph.getNodes()).forEach((tempNode)->{
    		if(planetDestination.equals(tempNode.getName())) {

    			List<Node> shortestPath = tempNode.getShortestPath();
    			
    			shortestPath.add(tempNode);
    	        routeResponse.setDistance(tempNode.getDistance());
    	        List<Route> routes = new ArrayList<Route>();
    	        
    	        for(int i=0;i<shortestPath.size()-1;i++) {
    	        	Node currentNode = shortestPath.get(i);
    	        	Node nextCurrentNode = shortestPath.get(i+1);
    				Route route = new Route();
        			route.setPlanetOrigin(currentNode.getName());
        			route.setPlanetDestination(nextCurrentNode.getName());
        			route.setDistance(nextCurrentNode.getDistance());
        			
        			route.setTrafficDelay(currentNode.getAdjacentNodes().get(nextCurrentNode).get("traficDelay"));
        			routes.add(route);
    	        }
    	        routeResponse.setListRoutes(routes);
    		}
    	});
		return routeResponse;
	}

}
