package com.example.BusTicketBooking.service;


import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Admin;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.AdminRepository;
@Repository
public class AdminService {
	
	private AdminRepository adminrepository;
	private UserService userService;
	

	public AdminService(AdminRepository adminrepository, UserService userService) {
		super();
		this.adminrepository = adminrepository;
		this.userService = userService;
	}


	public Admin createAdmin(Admin admin) {
		User user=admin.getUser();
		user.setRole("ADMIN");
		user=userService.signUp(user);
		admin.setUser(user);
		return adminrepository.save(admin);
	}

}
