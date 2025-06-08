package com.example.BusTicketBooking.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.CancelDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Cancellation;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.CancellationRepository;
import com.example.BusTicketBooking.repository.SeatRepository;

@Service
public class CancellationService {
	

	private final CancellationRepository cancellationRepo;
    private final BookingRepository bookingRepo;
    private final SeatRepository seatRepo;
    private final CancelDto cancelDto;



    public CancellationService(CancellationRepository cancellationRepo, BookingRepository bookingRepo,
			SeatRepository seatRepo, CancelDto cancelDto) {
		super();
		this.cancellationRepo = cancellationRepo;
		this.bookingRepo = bookingRepo;
		this.seatRepo = seatRepo;
		this.cancelDto = cancelDto;
	}
	public CancelDto cancelBooking(int bookingId, String reason) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Invalid Booking ID"));

        // Mark seats as AVAILABLE
        for (Seat seat : booking.getSeats()) {
            seat.setStatus("AVAILABLE");
            seatRepo.save(seat);
        }


        // Create Cancellation record
        Cancellation cancel = new Cancellation();
        cancel.setBooking(booking); // booking is now a persistent entity
        cancel.setCancelledAt(LocalDateTime.now());
        cancel.setRefundAmount(booking.getTotalFare());
        cancel.setReason(reason);
        
        // Mark booking as CANCELLED
        booking.setStatus("CANCELLED");
        bookingRepo.save(booking); // persist the status update

      //  return cancellationRepo.save(cancel); // booking exists → no FK error
        Cancellation saved = cancellationRepo.save(cancel);
        return cancelDto.convertCancellationToDto(saved);
        
    }
    public CancelDto getCancellationById(int id) {
        Cancellation cancellation = cancellationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Cancellation ID"));

        return cancelDto.convertCancellationToDto(cancellation);
    }


    /*public Cancellation getCancellationById(int id) {
        return cancellationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Cancellation ID"));
    }*/

}



