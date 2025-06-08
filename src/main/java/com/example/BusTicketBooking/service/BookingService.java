package com.example.BusTicketBooking.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.BookingDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.PassengerRepository;
import com.example.BusTicketBooking.repository.ScheduleRepository;
import com.example.BusTicketBooking.repository.SeatRepository;

@Service
public class BookingService {

	  
	    private BookingRepository bookingRepo;	  
	    private PassengerRepository passengerRepo;	
	    private ScheduleRepository scheduleRepo;
	    private SeatRepository seatRepo;
	    private BookingDto bookingDto;
	

		public BookingService(BookingRepository bookingRepo, PassengerRepository passengerRepo,
				ScheduleRepository scheduleRepo, SeatRepository seatRepo, BookingDto bookingDto) {
			super();
			this.bookingRepo = bookingRepo;
			this.passengerRepo = passengerRepo;
			this.scheduleRepo = scheduleRepo;
			this.seatRepo = seatRepo;
			this.bookingDto = bookingDto;
		}
		public BookingDto bookSeats(int scheduleId, List<Integer> seatIds, String username) {
	        Schedule schedule = scheduleRepo.findById(scheduleId)
	                .orElseThrow(() -> new RuntimeException("Invalid schedule"));

	        Passenger passenger = passengerRepo.getPassengerByUsername(username);

	        List<Seat> selectedSeats = seatRepo.findAllById(seatIds);

	        // Ensure all selected seats are available
	        for (Seat s : selectedSeats) {
	            if (!s.getStatus().equals("AVAILABLE")) {
	                throw new RuntimeException("Some seats are already booked");
	            }
	        }

	        // Calculate total fare
	        double totalFare = schedule.getFarePerSeat() * selectedSeats.size();

	        // Mark seats as booked
	        for (Seat s : selectedSeats) {
	            s.setStatus("BOOKED");
	            seatRepo.save(s);
	        }

	        // Create booking
	        Booking booking = new Booking();
	        booking.setPassenger(passenger);
	        booking.setSchedule(schedule);
	        booking.setSeats(selectedSeats);
	        booking.setTotalFare(totalFare);
	        booking.setStatus("CONFRIMED");
	        booking.setBookingTime(LocalDateTime.now());

	        
	        Booking saved = bookingRepo.save(booking);

	        return bookingDto.convertBookingIntoDto(saved);
	    }
/*/WITHOUT DTO 
	    public Booking getBookingById(int id) {
	        return bookingRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Invalid Booking ID"));
	    }*/
	    public BookingDto getBookingById(int id) {
	        Booking booking = bookingRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Invalid Booking ID"));

	        return bookingDto.convertBookingIntoDto(booking);
	    }
	    

	    public void cancelBooking(int id) {
	        Booking booking = bookingRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Invalid Booking ID"));

	        // Unbook seats
	        for (Seat s : booking.getSeats()) {
	            s.setStatus("AVAILABLE");
	            seatRepo.save(s);
	        }

	        bookingRepo.deleteById(id);
	    }
	}



