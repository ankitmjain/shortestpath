package com.dhs.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dhs.entity.Planet;

public interface PlanetRepository extends CrudRepository<Planet,Long> {

	Planet findByPlanetID(Long planetID);
	Planet findByPlanetName(String planetName);
	List<Planet> findByPlanetNode(String planetNode);
}
