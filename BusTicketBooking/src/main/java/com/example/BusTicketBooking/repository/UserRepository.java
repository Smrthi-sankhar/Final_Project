package com.example.BusTicketBooking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.username=?1")
	User getByUsername(String username);

}
