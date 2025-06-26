package com.example.BusTicketBooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.service.SeatService;

@RestController
@RequestMapping("/api/seat")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {
		@Autowired
	    private  SeatService seatService;
		


		    // Create seats for a schedule
		    @PostMapping("/generate/{scheduleId}")
		    public ResponseEntity<?> generateSeats(@PathVariable int scheduleId) {
		        return ResponseEntity.ok(seatService.generateSeats(scheduleId));
		    }

		    // View available seats
		    //@GetMapping("/available/{scheduleId}")
		    //public ResponseEntity<?> getAvailableSeats(@PathVariable int scheduleId) {
		      //  return ResponseEntity.ok(seatService.getAvailableSeats(scheduleId));
		    //}

		    // View all seats for a schedule
		    @GetMapping("/all/{scheduleId}")
		    public ResponseEntity<?> getAllSeats(@PathVariable int scheduleId) {
		        return ResponseEntity.ok(seatService.getAllSeatsBySchedule(scheduleId));
		    }
		    
		    @GetMapping("seats/{scheduleId}")
		    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable int scheduleId) {
		        List<Seat> availableSeats = seatService.findByScheduleIdAndStatus(scheduleId, "AVAILABLE");
		        return ResponseEntity.ok(availableSeats);
		    }

		}




