package com.dhs.utility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

        source.setDistance(0.0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Entry<Node,HashMap<String,Double>> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
            	
                Node adjacentNode = adjacencyPair.getKey();
                HashMap<String,Double> distanceValues = adjacencyPair.getValue(); 
                
                Double edgeWeigh = distanceValues.get("distance")+distanceValues.get("traficDelay");

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    private static void CalculateMinimumDistance(Node evaluationNode, Double edgeWeigh, Node sourceNode) {
        Double sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        Double lowestDistance = Double.MAX_VALUE;
        for (Node node : unsettledNodes) {
            Double nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    
    
    public static void main(String[] args) {
    	
    	Graph graph = new Graph();
    	
    	Node nodeA = new Node("A");
    	Node nodeB = new Node("B");
    	Node nodeC = new Node("C");
    	Node nodeD = new Node("D"); 
    	Node nodeE = new Node("E");
    	Node nodeF = new Node("F");
    	 
    	/*nodeA.addDestination(nodeB, 10);
    	nodeA.addDestination(nodeC, 15);
    	 
    	nodeB.addDestination(nodeD, 12);
    	nodeB.addDestination(nodeF, 15);
    	 
    	nodeC.addDestination(nodeE, 10);
    	 
    	nodeD.addDestination(nodeE, 2);
    	nodeD.addDestination(nodeF, 1);
    	 
    	nodeF.addDestination(nodeE, 5);*/
    	
    	graph.addNode(nodeA);
    	graph.addNode(nodeB);
    	graph.addNode(nodeC);
    	graph.addNode(nodeD);
    	graph.addNode(nodeE);
    	graph.addNode(nodeF);
    	
    	graph = Dijkstra.calculateShortestPathFromSource(graph,nodeB);
    	
    	(graph.getNodes()).forEach((tempNode)->{
    		System.out.println(tempNode.getName()+" "+tempNode.getDistance()+"...."+tempNode.getShortestPath());
    	});
    	
    }
}