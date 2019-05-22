package com.dhs.utility;

import java.util.PriorityQueue;

import com.dhs.entity.Route;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Dijkstras_Shortest_Path{
	static HashMap<String,Vertex> map = new HashMap();
	public static void vertexEdgeCreation(List<Route> routeList) {
		
		routeList.forEach((temp)->{
			
			Vertex originMap = map.get(temp.getPlanetOrigin());
			Vertex destinationMap = map.get(temp.getPlanetDestination());
			if(originMap==null)
				originMap = new Vertex(temp.getPlanetOrigin());
			if(destinationMap==null)
				destinationMap = new Vertex(temp.getPlanetDestination());
			originMap.getAdjacencies().add(new Edge(destinationMap,temp.getDistance(),temp.getTrafficDelay()));
			map.put(temp.getPlanetOrigin(),originMap);
			map.put(temp.getPlanetDestination(),destinationMap);
		});
		System.out.println(map);
	}
	
    public static double computePaths(String sourceP,String destinationP){
    	Vertex source = map.get(sourceP);
    	System.out.println(source.adjacencies.size());
        Vertex destination = map.get(destinationP);
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies){
                Vertex v = e.target;
                double weight = e.weight;
                double delay = e.tdelay;
                double distanceThroughU = u.minDistance + (weight+delay);
                if (distanceThroughU < v.minDistance) {
                    vertexQueue.remove(v);

                    v.minDistance = distanceThroughU ;
                    v.previous = u;
                    vertexQueue.add(v);
                }
            }
        }
        return destination.minDistance;
    }

    public static List<Route> getShortestPathTo(String planetDestination)
    {
    	Vertex target = map.get(planetDestination);
    	List<Route> routes = new ArrayList<Route>();
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous) {
        	path.add(vertex);
        }
        Collections.reverse(path);
        
        for(int i=0;i<path.size()-1;i++) {
        	Vertex currentVertex = path.get(i);
        	Vertex nextVertex = path.get(i+1);
        	List<Edge> edges = currentVertex.getAdjacencies();
        	for(int j=0;j<edges.size();j++) {
        		Edge currentEdge = edges.get(j);
        		if(currentEdge.target.getName().equals(nextVertex.getName())){
        			Route route = new Route();
        			route.setPlanetOrigin(currentVertex.getName());
        			route.setPlanetDestination(currentEdge.target.getName());
        			route.setDistance(currentEdge.weight);
        			route.setTrafficDelay(currentEdge.tdelay);
        			routes.add(route);break;
        		}
        	}
        	
        }
        return routes;
    }
}