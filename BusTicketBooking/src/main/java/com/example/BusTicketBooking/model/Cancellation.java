package com.example.BusTicketBooking.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Cancellation {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @OneToOne
	    private Booking booking;
	    private LocalDateTime cancelledAt;
	    private double refundAmount;
	    @Column(length = 1000)
	    private String reason;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Booking getBooking() {
			return booking;
		}
		public void setBooking(Booking booking) {
			this.booking = booking;
		}
		public LocalDateTime getCancelledAt() {
			return cancelledAt;
		}
		public void setCancelledAt(LocalDateTime cancelledAt) {
			this.cancelledAt = cancelledAt;
		}
		public double getRefundAmount() {
			return refundAmount;
		}
		public void setRefundAmount(double refundAmount) {
			this.refundAmount = refundAmount;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
	    

	   
	}

    


