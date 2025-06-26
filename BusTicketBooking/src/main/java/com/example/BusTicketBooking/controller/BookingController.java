package com.example.BusTicketBooking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.dto.BookingDto;
import com.example.BusTicketBooking.dto.BookingResponseDto;
import com.example.BusTicketBooking.service.BookingService;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:5173")
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
	    
	    @GetMapping("/{bookingId}")
	    public ResponseEntity<BookingDto> getBookingById(@PathVariable int bookingId) {
	        BookingDto dto = bookingService.getBookingDetailsById(bookingId);
	        return ResponseEntity.ok(dto);
	    }
	    
	    @GetMapping("/passenger/bookings")
	    public ResponseEntity<List<BookingResponseDto>> getPassengerBookings(Principal principal) {
	        String username = principal.getName(); //  JWT gives you the username
	        List<BookingResponseDto> bookings = bookingService.getBookingsByPassengerUsername(username);
	        return ResponseEntity.ok(bookings);
	    }



	    // View booking by ID
	    //@GetMapping("/get/{id}")
	   // public ResponseEntity<?> getBookingById(@PathVariable int id) {
	      //  return ResponseEntity.ok(bookingService.getBookingById(id));
	    //}

	    // Cancel booking
	    @PutMapping("/cancel/{id}")
	    public ResponseEntity<?> cancelBooking(@PathVariable int id) {
	        bookingService.cancelBooking(id);
	        return ResponseEntity.ok("Booking canceled and seats unbooked.");
	    }

}
