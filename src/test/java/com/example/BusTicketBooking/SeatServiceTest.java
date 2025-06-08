package com.example.BusTicketBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BusTicketBooking.dto.SeatDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.ScheduleRepository;
import com.example.BusTicketBooking.repository.SeatRepository;
import com.example.BusTicketBooking.service.SeatService;

@SpringBootTest
public class SeatServiceTest {
	    @Mock
	    private SeatRepository seatRepository;

	    @Mock
	    private ScheduleRepository scheduleRepository;

	    @Mock
	    private SeatDto seatDto;

	    @InjectMocks
	    private SeatService seatService;

	    private Schedule schedule;
	    private Bus bus;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);

	        // Setup Bus & Schedule
	        bus = new Bus();
	        bus.setTotalSeats(2);

	        schedule = new Schedule();
	        schedule.setId(1);
	        schedule.setBus(bus);
	    }

	    @Test
	    public void testGenerateSeats() {
	        when(scheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
	        when(seatRepository.save(any(Seat.class))).thenAnswer(i -> i.getArgument(0)); // return seat as saved

	        List<Seat> generatedSeats = seatService.generateSeats(1);

	        assertEquals(2, generatedSeats.size());
	        assertEquals("S1", generatedSeats.get(0).getSeatNumber());
	        assertEquals("AVAILABLE", generatedSeats.get(1).getStatus());
	        verify(seatRepository, times(2)).save(any(Seat.class));
	    }

	    @Test
	    public void testGetAvailableSeats() {
	        List<Seat> mockSeats = List.of(new Seat(), new Seat());

	        when(scheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
	        when(seatRepository.findByScheduleAndStatus(schedule, "AVAILABLE")).thenReturn(mockSeats);

	        List<com.example.BusTicketBooking.dto.SeatDto> mockDtoList = List.of(new com.example.BusTicketBooking.dto.SeatDto(), new com.example.BusTicketBooking.dto.SeatDto());
	        when(seatDto.convertSeatIntoDto(mockSeats)).thenReturn(mockDtoList);

	        List<com.example.BusTicketBooking.dto.SeatDto> result = seatService.getAvailableSeats(1);

	        assertEquals(2, result.size());
	        verify(seatDto).convertSeatIntoDto(mockSeats);
	    }

	    @Test
	    public void testGetAllSeatsBySchedule() {
	        List<Seat> mockSeats = List.of(new Seat(), new Seat(), new Seat());

	        when(scheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
	        when(seatRepository.findBySchedule(schedule)).thenReturn(mockSeats);

	        List<com.example.BusTicketBooking.dto.SeatDto> mockDtoList = List.of(
	                new com.example.BusTicketBooking.dto.SeatDto(),
	                new com.example.BusTicketBooking.dto.SeatDto(),
	                new com.example.BusTicketBooking.dto.SeatDto()
	        );

	        when(seatDto.convertSeatIntoDto(mockSeats)).thenReturn(mockDtoList);

	        List<com.example.BusTicketBooking.dto.SeatDto> result = seatService.getAllSeatsBySchedule(1);

	        assertEquals(3, result.size());
	        verify(seatDto).convertSeatIntoDto(mockSeats);
	    }

	    @Test
	    public void testBookSeat() {
	        Seat seat = new Seat();
	        seat.setId(1);
	        seat.setStatus("AVAILABLE");

	        when(seatRepository.findById(1)).thenReturn(Optional.of(seat));

	        seatService.bookSeat(1);

	        assertEquals("BOOKED", seat.getStatus());
	        verify(seatRepository).save(seat);
	    }

	    @Test
	    public void testUnbookSeat() {
	        Seat seat = new Seat();
	        seat.setId(1);
	        seat.setStatus("BOOKED");

	        when(seatRepository.findById(1)).thenReturn(Optional.of(seat));

	        seatService.unbookSeat(1);

	        assertEquals("AVAILABLE", seat.getStatus());
	        verify(seatRepository).save(seat);
	    }

	    @Test
	    public void testGenerateSeatsWithInvalidScheduleId() {
	        when(scheduleRepository.findById(99)).thenReturn(Optional.empty());

	        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
	            seatService.generateSeats(99);
	        });

	        assertEquals("Invalid Schedule ID", ex.getMessage());
	    }
	}


	
	   