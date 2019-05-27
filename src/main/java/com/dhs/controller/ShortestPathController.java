package com.dhs.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhs.entity.Planet;
import com.dhs.entity.Route;
import com.dhs.entity.RouteResponse;
import com.dhs.utility.RestApiCall;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ShortestPathController {

	ObjectMapper objectMapper = new ObjectMapper();
	
    @Autowired
    private RestApiCall restCall;
	
	@RequestMapping("/")
    public String blankRedire(){
        return "redirect:/home";
	}
	
	@GetMapping("/home")
    public String homePage(Model model) throws JsonParseException, JsonMappingException, IOException{
		ResponseEntity<String> response = restCall.getEndPointResponse("Planet/List",HttpMethod.GET,null);
		List<Planet> planetList = objectMapper.readValue(response.getBody(),new TypeReference<List<Planet>>(){});
		model.addAttribute("listofPlanets",planetList);
        return "index.html";
	}
	@GetMapping("/Planet/{planetCode}/Route")
    public String routeList(Model model,@PathVariable String planetCode) throws JsonParseException, JsonMappingException, IOException{
		ResponseEntity<String> response = restCall.getEndPointResponse("Planet/"+planetCode+"/Route",HttpMethod.GET,null);
		List<Route> routeList = objectMapper.readValue(response.getBody(),new TypeReference<List<Route>>(){});
		model.addAttribute("planetCode",planetCode);
		model.addAttribute("routeList",routeList);
        return "route.html";
	}
	
	@DeleteMapping("/Planet/{planetCode}/Delete")
    public String deletePlanet(Model model,@PathVariable String planetCode) throws JsonParseException, JsonMappingException, IOException{
		ResponseEntity<String> response = restCall.getEndPointResponse("Planet/"+planetCode+"/Delete",HttpMethod.DELETE,null);
		return "redirect:/home";
	}
	
	@GetMapping("/ShortestPath/{planetSource}/{planetDestination}")
    public String findShortestPath(Model model,@PathVariable String planetSource,@PathVariable String planetDestination) throws JsonParseException, JsonMappingException, IOException{
		ResponseEntity<String> response = restCall.getEndPointResponse("ShortestPath/"+planetSource+"/"+planetDestination,HttpMethod.GET,null);
		RouteResponse routeResponse = objectMapper.readValue(response.getBody(),new TypeReference<RouteResponse>(){});
		model.addAttribute("routeResponse",routeResponse);
		return "routeList.html::routeResponse";
	}
	
	@GetMapping("/ShowPlanetGraph")
    public String showPlanetGraph(Model model) throws JsonParseException, JsonMappingException, IOException{
		ResponseEntity<String> response = restCall.getEndPointResponse("Planet/List",HttpMethod.GET,null);
		List<Planet> planetList = objectMapper.readValue(response.getBody(),new TypeReference<List<Planet>>(){});
		model.addAttribute("listofPlanets",planetList);
		
		response = restCall.getEndPointResponse("Route/List",HttpMethod.GET,null);
		List<Route> routeList = objectMapper.readValue(response.getBody(),new TypeReference<List<Route>>(){});
		model.addAttribute("routeList",routeList);
		
        return "planetsGraph.html";
	}
	
}
