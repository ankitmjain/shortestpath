package com.dhs.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dhs.entity.Route;

public interface RouteRepository extends CrudRepository<Route,Long> {
	Route findByRouteID(Long routeID);
	List<Route> findByPlanetOrigin(String planetOrigin);
}
