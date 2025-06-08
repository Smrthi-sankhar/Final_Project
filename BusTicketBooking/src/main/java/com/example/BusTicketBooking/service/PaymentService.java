package com.example.BusTicketBooking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.PaymentDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Payment;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.PaymentRepository;

@Service
public class PaymentService {
	
	 	private final PaymentRepository paymentRepo;
	    private final BookingRepository bookingRepo;
	    private final  PaymentDto paymentDto;
		public PaymentService(PaymentRepository paymentRepo, BookingRepository bookingRepo, PaymentDto paymentDto) {
			super();
			this.paymentRepo = paymentRepo;
			this.bookingRepo = bookingRepo;
			this.paymentDto = paymentDto;
		}
		


		    public PaymentDto makePayment(int bookingId) {
		        Booking booking = bookingRepo.findById(bookingId)
		            .orElseThrow(() -> new RuntimeException("Invalid Booking ID"));

		        Payment payment = new Payment();
		        payment.setAmount(booking.getTotalFare());
		        payment.setStatus("PAID");
		        payment.setPaymentTime(LocalDateTime.now());
		        payment.setBooking(booking);

		        Payment savedPayment = paymentRepo.save(payment);

		        // Convert to DTO
		        PaymentDto dto = new PaymentDto();
		        dto.setId(savedPayment.getId());
		        dto.setBookingId(booking.getId());
		        dto.setPassengerName(booking.getPassenger().getName());
		        dto.setAmount(savedPayment.getAmount());
		        dto.setStatus(savedPayment.getStatus());
		        dto.setPaymentTime(savedPayment.getPaymentTime());

		        return dto;
		    }

		    public PaymentDto getPaymentById(int id) {
		        Payment payment = paymentRepo.findById(id)
		                .orElseThrow(() -> new RuntimeException("Invalid Payment ID"));

		        PaymentDto dto = new PaymentDto();
		        dto.setId(payment.getId());
		        dto.setBookingId(payment.getBooking().getId());
		        dto.setPassengerName(payment.getBooking().getPassenger().getName());
		        dto.setAmount(payment.getAmount());
		        dto.setStatus(payment.getStatus());
		        dto.setPaymentTime(payment.getPaymentTime());

		        return dto;
		    }

		    public List<PaymentDto> getAllPayments() {
		        List<Payment> list = paymentRepo.findAll();
		        return paymentDto.convertPaymentIntoDto(list);
		    }
		}



	    

	    