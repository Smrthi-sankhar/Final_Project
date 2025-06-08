package com.example.BusTicketBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BusTicketBooking.dto.ScheduleDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.repository.BusRepository;
import com.example.BusTicketBooking.repository.RouteRepository;
import com.example.BusTicketBooking.repository.ScheduleRepository;
import com.example.BusTicketBooking.service.ScheduleService;

@SpringBootTest
public class ScheduleServiceTest {

	    @Mock
	    private ScheduleRepository scheduleRepository;

	    @Mock
	    private BusRepository busRepository;

	    @Mock
	    private RouteRepository routeRepository;

	    @Mock
	    private ScheduleDto scheduleDto;

	    @InjectMocks
	    private ScheduleService scheduleService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testGetAllSchedules() {
	        Schedule schedule = new Schedule();
	        List<Schedule> scheduleList = List.of(schedule);
	        ScheduleDto dto = new ScheduleDto();

	        when(scheduleRepository.findAll()).thenReturn(scheduleList);
	        when(scheduleDto.convertScheduleIntoDto(scheduleList)).thenReturn(List.of(dto));

	        List<ScheduleDto> result = scheduleService.getAllSchedules();

	        assertEquals(1, result.size());
	        verify(scheduleRepository).findAll();
	    }

	    @Test
	    void testGetScheduleById() {
	        Schedule schedule = new Schedule();
	        schedule.setId(1);
	        ScheduleDto dto = new ScheduleDto();

	        when(scheduleRepository.findById(1)).thenReturn(Optional.of(schedule));
	        when(scheduleDto.convertScheduleIntoDto(List.of(schedule))).thenReturn(List.of(dto));

	        ScheduleDto result = scheduleService.getScheduleById(1);

	        assertNotNull(result);
	        verify(scheduleRepository).findById(1);
	    }

	    @Test
	    void testAddSchedule() {
	        Schedule schedule = new Schedule();
	        Bus bus = new Bus();
	        Route route = new Route();

	        when(busRepository.findById(1)).thenReturn(Optional.of(bus));
	        when(routeRepository.findById(2)).thenReturn(Optional.of(route));
	        when(scheduleRepository.save(schedule)).thenReturn(schedule);

	        Object result = scheduleService.addSchedule(schedule, 1, 2);

	        assertNotNull(result);
	        verify(scheduleRepository).save(schedule);
	    }

	    @Test
	    void testUpdateSchedule() {
	        Schedule existing = new Schedule();
	        existing.setFarePerSeat(500);
	        existing.setDepartureDate(LocalDate.now());

	        Schedule updated = new Schedule();
	        updated.setFarePerSeat(600);
	        updated.setDepartureDate(LocalDate.of(2025, 6, 10));

	        when(scheduleRepository.findById(1)).thenReturn(Optional.of(existing));
	        when(scheduleRepository.save(any(Schedule.class))).thenReturn(existing);

	        Schedule result = scheduleService.updateSchedule(1, updated);

	        assertEquals(600, result.getFarePerSeat());
	        assertEquals(LocalDate.of(2025, 6, 10), result.getDepartureDate());
	    }

	    @Test
	    void testDeleteSchedule() {
	        doNothing().when(scheduleRepository).deleteById(1);
	        scheduleService.deleteSchedule(1);
	        verify(scheduleRepository).deleteById(1);
	    }
	}



