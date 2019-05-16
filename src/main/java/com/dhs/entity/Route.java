package com.dhs.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "routes", schema = "sorttestpath")
public @Data class Route implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long routeID;
	private String planetOrigin;
	private String planetDestination;
	private Double distance;
	private Double trafficDelay;
	
}
