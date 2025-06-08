package com.example.BusTicketBooking.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.BusTicketBooking.model.Payment;

@Component
public class PaymentDto {
    private int id;
    private int bookingId;
    private String passengerName;
    private double amount;
    private String status; // e.g., PAID, FAILED, PENDING
    private LocalDateTime paymentTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}
    
	 public List<PaymentDto> convertPaymentIntoDto(List<Payment> list) {
	        List<PaymentDto> listDto = new ArrayList<>();
	        list.stream().forEach(payment -> {
	        	PaymentDto dto = new PaymentDto();
	            dto.setId(payment.getId());
	            dto.setBookingId(payment.getBooking().getId());
	            dto.setPassengerName(payment.getBooking().getPassenger().getName());
	            dto.setAmount(payment.getAmount());
	            dto.setStatus(payment.getStatus());
	            dto.setPaymentTime(payment.getPaymentTime());
	            listDto.add(dto);
	        });

	        return listDto;
	    }


}
