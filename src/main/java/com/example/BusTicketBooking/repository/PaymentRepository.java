package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{
	boolean existsByBookingId(int bookingId);


}
