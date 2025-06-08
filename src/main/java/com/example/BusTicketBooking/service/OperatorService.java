package com.example.BusTicketBooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.OperatorRepository;
@Service
public class OperatorService {
	
	private OperatorRepository operatorRepository;
	private UserService userservice;
	
	
	public OperatorService(OperatorRepository operatorRepository, UserService userservice) {
		super();
		this.operatorRepository = operatorRepository;
		this.userservice = userservice;
	}

	public Operator insertoperator(Operator operator) {
		User user=operator.getUser();
		user.setRole("OPERATOR");
		user=userservice.signUp(user);
		operator.setUser(user);
		return operatorRepository.save(operator);
	}
	
	public List<Operator> getAllOperators() {	
		return operatorRepository.findAll() ;
	}

	public void deleteOperator(int id) {
		 operatorRepository.deleteById(id);
		
	}

	public Operator getLearnerByUsername(String username) {
		
		return operatorRepository.getPassengerByUsername(username);
	}

}
