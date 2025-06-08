package com.example.BusTicketBooking.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.BusTicketBooking.model.Seat;

@Component
public class SeatDto {
	 	private int id;
	    private String seatNumber;
	    private String status; // AVAILABLE or BOOKED
	    private int scheduleId;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSeatNumber() {
			return seatNumber;
		}
		public void setSeatNumber(String seatNumber) {
			this.seatNumber = seatNumber;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(int scheduleId) {
			this.scheduleId = scheduleId;
		}
	    
		 public List<SeatDto> convertSeatIntoDto(List<Seat> list) {
		        List<SeatDto> listDto = new ArrayList<>();
		        list.stream().forEach(seat -> {
		        	SeatDto dto = new SeatDto();
		            dto.setId(seat.getId());
		            dto.setSeatNumber(seat.getSeatNumber());
		            dto.setStatus(seat.getStatus());
		            dto.setScheduleId(seat.getSchedule().getId()); 
		            listDto.add(dto);
		        });

		        return listDto;
		    }

}
