package com.example.BusTicketBooking.controller;

import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(origins = "http://localhost:5173")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Add route
    @PostMapping("/add")
    public Route insertRoute(@RequestBody Route route) {
        return routeService.insertRoute(route);
    }

    // Get all routes
    @GetMapping("/get-all")
    public ResponseEntity<List<Route>> getAllRoutes() {
        List<Route> routes = routeService.getAllRoutes();
        return ResponseEntity.status(HttpStatus.OK).body(routes);
    }

    // Get route by ID(for edit)
    @GetMapping("/get/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable int id) {
        Route route = routeService.getRouteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    // Update route by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable int id, @RequestBody Route updatedRoute) {
        Route route = routeService.updateRoute(id, updatedRoute);
        return ResponseEntity.status(HttpStatus.OK).body(route);
    }

    // Delete route by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable int id) {
        routeService.deleteRoute(id);
        return ResponseEntity.status(HttpStatus.OK).body("Route deleted successfully");
    }
}
