package com.example.BusTicketBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.model.Admin;
import com.example.BusTicketBooking.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	@Autowired
	private AdminService adminservice;
	@PostMapping("/add")
	public Admin createAdmin(@RequestBody Admin admin) {
		return adminservice.createAdmin(admin);
		
	}
	

}
