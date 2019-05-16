package com.dhs.dao;

import org.springframework.data.repository.CrudRepository;

import com.dhs.entity.Planet;

public interface PlanetRepository extends CrudRepository<Planet,Long> {

	Planet findByPlanetName(String planetName);
}
