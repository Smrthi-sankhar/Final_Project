package com.example.BusTicketBooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.exception.ResourceNotFoundException;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.repository.RouteRepository;

@Service
public class RouteService {

	private final RouteRepository routeRepository;

	public RouteService(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	// for insert
	public Route insertRoute(Route route) {
		return routeRepository.save(route);
	}

	// for get all
	public List<Route> getAllRoutes() {
		return routeRepository.findAll();
	}

	// get by specific id
	public Route getRouteById(int id) {
		return routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Route ID is invalid: " + id));
	}

	// for update
	public Route updateRoute(int id, Route updatedRoute) {
		Route dbRoute = routeRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Route ID: " + id));

		if (updatedRoute.getSource() != null) {
			dbRoute.setSource(updatedRoute.getSource());
		}
		if (updatedRoute.getDestination() != null) {
			dbRoute.setDestination(updatedRoute.getDestination());
		}
		if (updatedRoute.getDistance() != null) {
			dbRoute.setDistance(updatedRoute.getDistance());
		}
		if (updatedRoute.getDuration() != null) {
			dbRoute.setDuration(updatedRoute.getDuration());
		}
		return routeRepository.save(dbRoute);
	}

	// for delete
	public void deleteRoute(int id) {
		Route route = routeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Route with ID " + id + " not found"));
		routeRepository.delete(route);
	}
}
