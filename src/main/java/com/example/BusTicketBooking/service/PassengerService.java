package com.example.BusTicketBooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.PassengerRepository;



@Service
public class PassengerService {
	
	private PassengerRepository passengerRepository;
	private UserService userservice;

	

    public PassengerService(PassengerRepository passengerRepository, UserService userservice) {
		super();
		this.passengerRepository = passengerRepository;
		this.userservice = userservice;
	}

	public Passenger insertpassenger(Passenger passenger) {
    	User user =passenger.getUser();
    	user.setRole("PASSENGER");
    	user =userservice.signUp(user);
    	passenger.setUser(user);
		return passengerRepository.save(passenger);
	}

	public List<Passenger> getAllPassengers() {
		
		return passengerRepository.findAll() ;
	}

	public void deletePassenger(int id) {
		Passenger passenger=passengerRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid id"));
		passengerRepository.delete(passenger);
	}

	public Passenger getPassengerByUsername(String username) {
		return passengerRepository.getPassengerByUsername(username);
		
		
	}
	

	
	
}
