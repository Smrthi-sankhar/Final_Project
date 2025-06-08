package com.example.BusTicketBooking.repository;

import com.example.BusTicketBooking.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {
    
}

