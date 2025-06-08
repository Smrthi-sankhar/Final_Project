package com.example.BusTicketBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BusTicketBooking.dto.BookingDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.PassengerRepository;
import com.example.BusTicketBooking.repository.ScheduleRepository;
import com.example.BusTicketBooking.repository.SeatRepository;
import com.example.BusTicketBooking.service.BookingService;
@SpringBootTest
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private PassengerRepository passengerRepo;

    @Mock
    private ScheduleRepository scheduleRepo;

    @Mock
    private SeatRepository seatRepo;

    @Mock
    private BookingDto bookingDto; // DTO is mocked

    @InjectMocks
    private BookingService bookingService;

    private Schedule schedule;
    private Passenger passenger;
    private Seat seat1, seat2;
    private Booking booking;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new BookingService(bookingRepo, passengerRepo, scheduleRepo, seatRepo, bookingDto);

        // Setup mock Schedule
        schedule = new Schedule();
        schedule.setId(1);
        schedule.setFarePerSeat(500);

        Route route = new Route();
        route.setSource("Chennai");
        route.setDestination("Bangalore");

        Bus bus = new Bus();
        bus.setBusNumber("TN01AB1234");
        bus.setBusType("AC");
        bus.setRoute(route);
        schedule.setBus(bus);

        // Setup mock Passenger
        passenger = new Passenger();
        passenger.setId(1);
        passenger.setName("Smrthi");
        passenger.setContact("9876543210");

        // Setup mock Seats
        seat1 = new Seat();
        seat1.setId(1);
        seat1.setSeatNumber("A1");
        seat1.setStatus("AVAILABLE");

        seat2 = new Seat();
        seat2.setId(2);
        seat2.setSeatNumber("A2");
        seat2.setStatus("AVAILABLE");

        // Setup mock Booking
        booking = new Booking();
        booking.setId(100);
        booking.setPassenger(passenger);
        booking.setSchedule(schedule);
        booking.setSeats(List.of(seat1, seat2));
        booking.setTotalFare(1000);
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());
    }

    @Test
    public void testBookSeatsSuccess() {
        when(scheduleRepo.findById(1)).thenReturn(Optional.of(schedule));
        when(passengerRepo.getPassengerByUsername("smrthi@gmail.com")).thenReturn(passenger);
        when(seatRepo.findAllById(List.of(1, 2))).thenReturn(List.of(seat1, seat2));
        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);

        BookingDto expectedDto = new BookingDto();
        expectedDto.setId(100);
        expectedDto.setPassengerName("Smrthi");
        expectedDto.setContactNo("9876543210");
        expectedDto.setBusNumber("TN01AB1234");
        expectedDto.setBusType("AC");
        expectedDto.setSource("Chennai");
        expectedDto.setDestination("Bangalore");
        expectedDto.setFarePerSeat(500);
        expectedDto.setTotalFare(1000);
        expectedDto.setSeatNumber(List.of("A1", "A2"));
        expectedDto.setScheduleId(1);
        expectedDto.setBookingTime(booking.getBookingTime());

        when(bookingDto.convertBookingIntoDto(any(Booking.class))).thenReturn(expectedDto);

        BookingDto result = bookingService.bookSeats(1, List.of(1, 2), "smrthi@gmail.com");

        assertEquals(expectedDto.getPassengerName(), result.getPassengerName());
        assertEquals(expectedDto.getTotalFare(), result.getTotalFare());
        assertEquals(expectedDto.getSeatNumber(), result.getSeatNumber());
    }

    @Test
    public void testBookSeatsFailWhenSeatAlreadyBooked() {
        seat1.setStatus("BOOKED");
        when(scheduleRepo.findById(1)).thenReturn(Optional.of(schedule));
        when(passengerRepo.getPassengerByUsername("smrthi@gmail.com")).thenReturn(passenger);
        when(seatRepo.findAllById(List.of(1, 2))).thenReturn(List.of(seat1, seat2));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            bookingService.bookSeats(1, List.of(1, 2), "smrthi@gmail.com");
        });

        assertEquals("Some seats are already booked", exception.getMessage());
    }

    @Test
    public void testGetBookingByIdSuccess() {
        when(bookingRepo.findById(100)).thenReturn(Optional.of(booking));

        BookingDto expectedDto = new BookingDto();
        expectedDto.setId(100);
        expectedDto.setPassengerName("Smrthi");

        when(bookingDto.convertBookingIntoDto(booking)).thenReturn(expectedDto);

        BookingDto result = bookingService.getBookingById(100);
        assertEquals(100, result.getId());
        assertEquals("Smrthi", result.getPassengerName());
    }

    @Test
    public void testCancelBookingSuccess() {
        when(bookingRepo.findById(100)).thenReturn(Optional.of(booking));

        bookingService.cancelBooking(100);

        verify(seatRepo, times(2)).save(any(Seat.class)); // For 2 seats
        verify(bookingRepo, times(1)).deleteById(100);
    }
}



	  

