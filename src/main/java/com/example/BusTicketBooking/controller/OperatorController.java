package com.example.BusTicketBooking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.service.OperatorService;

@RestController
@RequestMapping("/api/operator")
public class OperatorController {
	@Autowired
	private OperatorService operatorService;
	
	@PostMapping("/add")
    public Operator insertOperator(@RequestBody Operator operator) {
        return operatorService.insertoperator(operator);
    }
	
	 @GetMapping("/all")
	    public List<Operator> getAllOperators() {
	        return operatorService.getAllOperators();
	    }
	 
	    @GetMapping("/get-one")
	    public Operator operatorById(Principal principal) {
	    	// Ask spring username of loggedIn user using Principal interface 
	    	String username = principal.getName(); 
	    	return operatorService.getLearnerByUsername(username) ;
	    }
	    
	    @DeleteMapping("delete/{Id}")
	    public void deleteOperator(@PathVariable int Id) {
	    	operatorService.deleteOperator(Id);
	    }
}
