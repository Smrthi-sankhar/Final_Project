package com.example.BusTicketBooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.Schedule;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	@Query("SELECT p FROM Passenger p WHERE p.user.username = ?1")

	Passenger getPassengerByUsername(String username);
	
	@Query("SELECT p FROM Passenger p WHERE p.user.username = ?1")
	Optional<Passenger> findPassengerByUsername(String username);


	

	




}
