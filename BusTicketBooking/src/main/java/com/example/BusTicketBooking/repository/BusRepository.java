package com.example.BusTicketBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Operator;
@Repository
public interface BusRepository  extends JpaRepository<Bus, Integer>{
	@Query("SELECT b FROM Bus b WHERE b.operator.user.username = ?1")
	List<Bus> getByOperatorname(String name);
	
	List<Bus> findByOperator(Operator operator);



}
