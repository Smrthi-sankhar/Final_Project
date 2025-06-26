package com.example.BusTicketBooking.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking{

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private double totalFare;
	    @ManyToOne
	    private Passenger passenger;
	    @ManyToOne
	    private Schedule schedule;
	  
	  
		private LocalDateTime bookingTime;
	    private String status; // e.g., "CONFIRMED", "CANCELLED"

		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getTotalFare() {
			return totalFare;
		}
		public void setTotalFare(double totalFare) {
			this.totalFare = totalFare;
		}
		public Passenger getPassenger() {
			return passenger;
		}
		public void setPassenger(Passenger passenger) {
			this.passenger = passenger;
		}
		public Schedule getSchedule() {
			return schedule;
		}
		public void setSchedule(Schedule schedule) {
			this.schedule = schedule;
		}
	
		public LocalDateTime getBookingTime() {
			return bookingTime;
		}
		public void setBookingTime(LocalDateTime bookingTime) {
			this.bookingTime = bookingTime;
		}
	    
	    
	}

	
 

	
	
	
