package com.example.BusTicketBooking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.SeatDto;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.model.Seat;
import com.example.BusTicketBooking.repository.ScheduleRepository;
import com.example.BusTicketBooking.repository.SeatRepository;

@Service
public class SeatService {
			 private  SeatRepository seatRepository;
			 private ScheduleRepository scheduleRepository;
			 @Autowired
			 private SeatDto seatDto;

		        public SeatService(SeatRepository seatRepository, ScheduleRepository scheduleRepository) {
				super();
				this.seatRepository = seatRepository;
				this.scheduleRepository = scheduleRepository;
			}
		        
		       


		public List<Seat> generateSeats(int scheduleId) {
            Schedule schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new RuntimeException("Invalid Schedule ID"));

            int totalSeats = schedule.getBus().getTotalSeats();
            List<Seat> allSeats = new ArrayList<>();

            for (int i = 1; i <= totalSeats; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber("S" + i);
                seat.setStatus("AVAILABLE");
                seat.setSchedule(schedule);
                allSeats.add(seatRepository.save(seat));
            }

            return allSeats;
        }    /*public List<Seat> getAvailableSeats(int scheduleId) {
    	Schedule schedule = scheduleRepository.findById(scheduleId)
	    .orElseThrow(() -> new RuntimeException("Invalid Schedule ID"));

return seatRepository.findByScheduleAndStatus(schedule, "AVAILABLE");
}*/
	       /* public List<Seat> getAllSeatsBySchedule(int scheduleId) {
    	Schedule schedule = scheduleRepository.findById(scheduleId)
			    .orElseThrow(() -> new RuntimeException("Invalid Schedule ID"));

        return seatRepository.findBySchedule(schedule);
    }*/
    
	

    
        public List<SeatDto> getAvailableSeats(int scheduleId) {
            Schedule schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new RuntimeException("Invalid Schedule ID"));

            List<Seat> seatList = seatRepository.findByScheduleAndStatus(schedule, "AVAILABLE");

            return seatDto.convertSeatIntoDto(seatList);
        }



        public List<SeatDto> getAllSeatsBySchedule(int scheduleId) {
            Schedule schedule = scheduleRepository.findById(scheduleId)
                    .orElseThrow(() -> new RuntimeException("Invalid Schedule ID"));

            List<Seat> seatList = seatRepository.findBySchedule(schedule);

            return seatDto.convertSeatIntoDto(seatList);
        }


        public void bookSeat(int seatId) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Invalid Seat ID"));
            seat.setStatus("BOOKED");
            seatRepository.save(seat);
        }

        public void unbookSeat(int seatId) {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new RuntimeException("Invalid Seat ID"));
            seat.setStatus("AVAILABLE");
            seatRepository.save(seat);
        }
    }

