package com.example.BusTicketBooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.BusTicketBooking.model.Schedule;

@Component
public class ScheduleDto {
	
	private int id;
	private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double farePerSeat;
    private int busId;
    private String busNumber;
    private String busType;
   // private int totalSeats;
    private int routeId;
    private String source;
    private String destination;
    private Double distance; 
    private Double duration;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public double getFarePerSeat() {
		return farePerSeat;
	}
	public void setFarePerSeat(double farePerSeat) {
		this.farePerSeat = farePerSeat;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	/*public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}*/
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
    
	 public List<ScheduleDto> convertScheduleIntoDto(List<Schedule> list) {
	        List<ScheduleDto> listDto = new ArrayList<>();
	        list.stream().forEach(schedule -> {
	        	ScheduleDto dto = new ScheduleDto();
	            dto.setId(schedule.getId());
	            dto.setDepartureDate(schedule.getDepartureDate());
	            dto.setDepartureTime(schedule.getDepartureTime());
	            dto.setArrivalTime(schedule.getArrivalTime());
	            dto.setFarePerSeat(schedule.getFarePerSeat());
	            dto.setBusId(schedule.getBus().getId());
	            dto.setBusNumber(schedule.getBus().getBusNumber());
	            dto.setBusType(schedule.getBus().getBusType());
	            //dto.setTotalSeats(schedule.getBus().getTotalSeats());
	            dto.setRouteId(schedule.getRoute().getId());
	            dto.setSource(schedule.getRoute().getSource());
	            dto.setDestination(schedule.getRoute().getDestination());
	            dto.setDistance(schedule.getRoute().getDistance());
	            dto.setDuration(schedule.getRoute().getDuration());
	            
	            listDto.add(dto);
	        });

	        return listDto;
	    }


}
