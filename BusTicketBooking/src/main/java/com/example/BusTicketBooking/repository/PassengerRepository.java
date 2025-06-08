package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BusTicketBooking.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	@Query("SELECT p FROM Passenger p WHERE p.user.username = ?1")

	Passenger getPassengerByUsername(String username);




}
