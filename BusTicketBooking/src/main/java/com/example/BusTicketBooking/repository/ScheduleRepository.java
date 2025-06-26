package com.example.BusTicketBooking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
	@Query("SELECT s FROM Schedule s " +
		     "JOIN s.bus b " +
		  "JOIN b.route r " +
		    "WHERE r.source = ?1 AND r.destination = ?2 AND s.departureDate = ?3")
		List<Schedule> searchBuses(String source, String destination, LocalDate date);
	
	//@Query("SELECT s FROM Schedule s WHERE LOWER(s.bus.route.source) = LOWER(:source) AND LOWER(s.bus.route.destination) = LOWER(:destination) AND s.departureDate = :date")
	//List<Schedule> searchBuses(@Param("source") String source, @Param("destination") String destination, @Param("date") LocalDate date);


}
