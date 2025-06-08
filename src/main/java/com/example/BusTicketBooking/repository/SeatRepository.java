package com.example.BusTicketBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.model.Seat;

@Repository
public interface SeatRepository  extends JpaRepository<Seat, Integer>{


		List<Seat> findByScheduleAndStatus(Schedule schedule, String status);

		List<Seat> findBySchedule(Schedule schedule);
		

}
