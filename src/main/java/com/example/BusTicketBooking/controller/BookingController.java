package com.example.BusTicketBooking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	 @Autowired
	    private BookingService bookingService;

	    // Book seats
	    @PostMapping("/add/{scheduleId}")
	    public ResponseEntity<?> createBooking(@PathVariable int scheduleId,
	                                           @RequestBody List<Integer> seatIds,
	                                           Principal principal) {
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(bookingService.bookSeats(scheduleId, seatIds, principal.getName()));
	    }


	    // View booking by ID
	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getBookingById(@PathVariable int id) {
	        return ResponseEntity.ok(bookingService.getBookingById(id));
	    }

	    // Cancel booking
	    @DeleteMapping("/cancel/{id}")
	    public ResponseEntity<?> cancelBooking(@PathVariable int id) {
	        bookingService.cancelBooking(id);
	        return ResponseEntity.ok("Booking canceled and seats unbooked.");
	    }

}
