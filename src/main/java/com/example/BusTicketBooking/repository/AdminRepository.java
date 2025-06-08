package com.example.BusTicketBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BusTicketBooking.model.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
