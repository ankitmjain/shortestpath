package com.dhs.entity;

import java.io.Serializable;

import lombok.Data;

public @Data class RouteResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String planetOrigin;
	private String planetDestination;
	private Double distance;
	private Iterable<Route> listRoutes;
	
}
