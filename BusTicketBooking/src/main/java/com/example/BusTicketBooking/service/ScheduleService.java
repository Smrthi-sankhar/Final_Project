package com.example.BusTicketBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.BusTicketBooking.dto.ScheduleDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.repository.BusRepository;
import com.example.BusTicketBooking.repository.RouteRepository;
import com.example.BusTicketBooking.repository.ScheduleRepository;

@Service
public class ScheduleService {
	
	private ScheduleRepository scheduleRepository;
	private BusRepository busRepository; 
	private RouteRepository routeRepository;
	 @Autowired
		private ScheduleDto scheduleDto;
	

	public ScheduleService(ScheduleRepository scheduleRepository, BusRepository busRepository,
			RouteRepository routeRepository) {
		super();
		this.scheduleRepository = scheduleRepository;
		this.busRepository = busRepository;
		this.routeRepository = routeRepository;
	}



	public Object addSchedule(Schedule schedule, int busId, int routeId) {
		Bus bus=busRepository.findById(busId)
				.orElseThrow(() ->new RuntimeException("Invalid id"));
		Route route=routeRepository.findById(routeId)
				.orElseThrow(() -> new RuntimeException("Invalid Id"));
		
		schedule.setBus(bus);
		schedule.setRoute(route);
		
		return scheduleRepository.save(schedule);
	}


	 public List<ScheduleDto> getAllSchedules() {
	        List<Schedule> list=scheduleRepository.findAll();
	        return scheduleDto.convertScheduleIntoDto(list);
	      }



	   public void deleteSchedule(int id) {
	        scheduleRepository.deleteById(id);
	    }


	 public Schedule updateSchedule(int id, Schedule updatedSchedule) {
	        Schedule dbSchedule = scheduleRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Schedule ID not found"));

	        if (updatedSchedule.getDepartureDate() != null)
	            dbSchedule.setDepartureDate(updatedSchedule.getDepartureDate());
	        if (updatedSchedule.getDepartureTime() != null)
	            dbSchedule.setDepartureTime(updatedSchedule.getDepartureTime());
	        if (updatedSchedule.getArrivalTime() != null)
	            dbSchedule.setArrivalTime(updatedSchedule.getArrivalTime());
	        if (updatedSchedule.getFarePerSeat() != 0.0)  // or != null if using wrapper class
	            dbSchedule.setFarePerSeat(updatedSchedule.getFarePerSeat());

	        return scheduleRepository.save(dbSchedule);
	    }



	public ScheduleDto  getScheduleById(int id) {
			Schedule schedule =	scheduleRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("invalid id"));
			return scheduleDto.convertScheduleIntoDto(List.of(schedule)).get(0);
		
	}

}
