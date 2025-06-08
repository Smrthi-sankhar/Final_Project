package com.example.BusTicketBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BusTicketBooking.dto.CancelDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Cancellation;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.CancellationRepository;
import com.example.BusTicketBooking.repository.SeatRepository;
import com.example.BusTicketBooking.service.CancellationService;

@SpringBootTest
public class CancellationServiceTest {
	    @Mock
	    private CancellationRepository cancellationRepo;

	    @Mock
	    private BookingRepository bookingRepo;

	    @Mock
	    private SeatRepository seatRepo;

	    @Mock
	    private CancelDto cancelDto;

	    private CancellationService cancellationService;

	    @BeforeEach
	    public void setUp() {
	        cancellationService = new CancellationService(cancellationRepo, bookingRepo, seatRepo, cancelDto);
	    }

	    @Test
	    public void testCancelBooking_Success() {
	        int bookingId = 1;
	        String reason = "Change of plan";

	        Booking booking = new Booking();
	        booking.setId(bookingId);
	        booking.setStatus("CONFIRMED");
	        booking.setTotalFare(800.0);

	        Seat seat = new Seat();
	        seat.setId(101);
	        seat.setStatus("BOOKED");
	        booking.setSeats(List.of(seat));


	        Cancellation savedCancel = new Cancellation();
	        savedCancel.setId(999);
	        savedCancel.setReason(reason);
	        savedCancel.setRefundAmount(800.0);
	        savedCancel.setBooking(booking);

	        CancelDto dto = new CancelDto();
	        dto.setId(999);
	        dto.setBookingId(bookingId);
	        dto.setReason(reason);
	        dto.setRefundAmount(800.0);

	        when(bookingRepo.findById(bookingId)).thenReturn(Optional.of(booking));
	        when(seatRepo.save(any(Seat.class))).thenReturn(seat);
	        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);
	        when(cancellationRepo.save(any(Cancellation.class))).thenReturn(savedCancel);
	        when(cancelDto.convertCancellationToDto(savedCancel)).thenReturn(dto);

	        CancelDto result = cancellationService.cancelBooking(bookingId, reason);

	        assertNotNull(result);
	        assertEquals(reason, result.getReason());
	        assertEquals(800.0, result.getRefundAmount());
	        assertEquals(bookingId, result.getBookingId());
	    }

	    @Test
	    public void testCancelBooking_BookingNotFound() {
	        int invalidBookingId = 404;

	        when(bookingRepo.findById(invalidBookingId)).thenReturn(Optional.empty());

	        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	            cancellationService.cancelBooking(invalidBookingId, "No reason");
	        });

	        assertEquals("Invalid Booking ID", exception.getMessage());
	        verify(bookingRepo).findById(invalidBookingId);
	    }

	    @Test
	    public void testGetCancellationById_Success() {
	        int cancelId = 1;

	        Booking booking = new Booking();
	        booking.setId(99);

	        Cancellation cancellation = new Cancellation();
	        cancellation.setId(cancelId);
	        cancellation.setBooking(booking);
	        cancellation.setReason("Bus delay");
	        cancellation.setRefundAmount(100.0);

	        CancelDto dto = new CancelDto();
	        dto.setId(cancelId);
	        dto.setBookingId(99);
	        dto.setReason("Bus delay");
	        dto.setRefundAmount(100.0);

	        when(cancellationRepo.findById(cancelId)).thenReturn(Optional.of(cancellation));
	        when(cancelDto.convertCancellationToDto(cancellation)).thenReturn(dto);

	        CancelDto result = cancellationService.getCancellationById(cancelId);

	        assertNotNull(result);
	        assertEquals(cancelId, result.getId());
	        assertEquals("Bus delay", result.getReason());
	        assertEquals(100.0, result.getRefundAmount());
	        assertEquals(99, result.getBookingId());

	        verify(cancellationRepo).findById(cancelId);
	    }

	    @Test
	    public void testGetCancellationById_NotFound() {
	        int invalidCancelId = 999;

	        when(cancellationRepo.findById(invalidCancelId)).thenReturn(Optional.empty());

	        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	            cancellationService.getCancellationById(invalidCancelId);
	        });

	        assertEquals("Invalid Cancellation ID", exception.getMessage());
	        verify(cancellationRepo).findById(invalidCancelId);
	    }
	}
