package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Booking;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Integer>{
	
	

}
