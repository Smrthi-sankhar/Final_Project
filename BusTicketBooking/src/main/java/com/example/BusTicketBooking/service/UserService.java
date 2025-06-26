package com.example.BusTicketBooking.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.OperatorRepository;
import com.example.BusTicketBooking.repository.PassengerRepository;
import com.example.BusTicketBooking.repository.UserRepository;




@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private PassengerRepository passengerRepository;
	private OperatorRepository operatorRepository;
	
	

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			PassengerRepository passengerRepository, OperatorRepository operatorRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.passengerRepository = passengerRepository;
		this.operatorRepository = operatorRepository;
	}

	public User signUp(User user) {
		// encrypt the pain text password given 
		String plainPassword = user.getPassword(); //<- this gives you plain password
		String encodedPassword =  passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); //<- Now, User has encoded password 
		
		// Save User in DB 
		return userRepository.save(user);
	}

	public Object getUserInfo(String username) {
		User user = userRepository.getByUsername(username);
		switch (user.getRole().toUpperCase()) {
			case "PASSENGER":
				Passenger passenger = passengerRepository.getPassengerByUsername(username);
				return passenger;
			case "OPERATOR":
			
				Operator operator = operatorRepository.getOperatorByUsername(username);
				
					return operator;
				
			case "EXECUTIVE":
				return null;
			default:
				return null;
		}
	}

}



	


	
	
	
