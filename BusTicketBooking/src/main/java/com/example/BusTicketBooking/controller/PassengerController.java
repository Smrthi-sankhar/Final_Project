package com.example.BusTicketBooking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.service.PassengerService;


@RestController
@RequestMapping("/api/passenger")
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	      
	    @PostMapping("/add")
	    public Passenger insertPassenger(@RequestBody Passenger passenger) {
	        return passengerService.insertpassenger(passenger);
	    }
	    
	    @GetMapping("/all")
	    public List<Passenger> getAllPassengers() {
	        return passengerService.getAllPassengers();
	    }
	    @GetMapping("/get-one")
	    public Passenger passengerById(Principal principal) {
	    	// Ask spring username of loggedIn user using Principal interface 
	    	String username = principal.getName(); 
	    	return passengerService.getPassengerByUsername(username) ;
	    }
	    
	    @DeleteMapping("/delete/{Id}")
	    public ResponseEntity<?> deletePassenger(@PathVariable int Id) {
	    	passengerService.deletePassenger(Id);
	    	return ResponseEntity.ok("deleted");
	    }
	    

}
