package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Cancellation;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation, Integer> {

}
