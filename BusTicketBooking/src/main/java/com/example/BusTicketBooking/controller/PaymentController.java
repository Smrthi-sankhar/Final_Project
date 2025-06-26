package com.example.BusTicketBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.BusTicketBooking.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
	
	    @Autowired
	    private PaymentService paymentService;

	    /*
	     * Make payment for a booking
	     * PATH: /api/payment/make/{bookingId}
	     * METHOD: POST
	     */
	    @PostMapping("/make/{bookingId}")
	    public ResponseEntity<?> makePayment(@PathVariable int bookingId) {
	        return ResponseEntity.ok(paymentService.makePayment(bookingId));
	    }

	    /*
	     * Get payment details
	     * PATH: /api/payment/get/{id}
	     * METHOD: GET
	     */
	    @GetMapping("/get/{id}")
	    public ResponseEntity<?> getPayment(@PathVariable int id) {
	        return ResponseEntity.ok(paymentService.getPaymentById(id));
	    }
	}
