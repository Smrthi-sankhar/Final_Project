package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.BusTicketBooking.model.Operator;


public interface OperatorRepository extends JpaRepository<Operator, Integer> {
			@Query("SELECT o FROM Operator o WHERE o.user.username = ?1")
			Operator getPassengerByUsername(String username);
		
}
