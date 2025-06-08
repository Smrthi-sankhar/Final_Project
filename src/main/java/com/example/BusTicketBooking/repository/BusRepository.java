package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Bus;
@Repository
public interface BusRepository  extends JpaRepository<Bus, Integer>{

}
