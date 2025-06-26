package com.example.BusTicketBooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.dto.CancelDto;
import com.example.BusTicketBooking.service.CancellationService;

@RestController
@RequestMapping("/api/cancel")
@CrossOrigin(origins = "http://localhost:5173")
public class CancellationController {
	
	private final CancellationService cancellationService;

    public CancellationController(CancellationService cancellationService) {
        this.cancellationService = cancellationService;
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable int bookingId,@RequestBody CancelDto request){                                      
        return ResponseEntity.ok(cancellationService.cancelBooking(bookingId,request.getReason()));
    }



    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCancellation(@PathVariable int id) {
        return ResponseEntity.ok(cancellationService.getCancellationById(id));
    }
}


