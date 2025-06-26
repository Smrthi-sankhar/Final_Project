package com.example.BusTicketBooking.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {
			@Query("SELECT o FROM Operator o WHERE o.user.username = ?1")
			Operator getOperatorByUsername (String username);
			@Query("SELECT o FROM Operator o WHERE o.user.username = ?1")
			Optional<Operator> findOperatorByUsername (String username);

		
}
