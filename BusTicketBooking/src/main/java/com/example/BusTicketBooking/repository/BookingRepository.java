package com.example.BusTicketBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Booking;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Integer>{

	List<Booking> findByPassengerId(int id);
	
	@Query("SELECT COUNT(b.passenger.id) FROM Booking b WHERE b.schedule.bus.id = :busId")
	long countPassengersByBusId(@Param("busId") int busId);



	
	

}
