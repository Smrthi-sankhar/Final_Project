package com.example.BusTicketBooking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BusSearchResponseDto {
	private int scheduleId; 
	 	private String busNumber;
	    private String busType;
	    private int totalSeats;
	    
		private String source;
	    private String destination;
	    private LocalTime departureTime;
	    private LocalTime arrivalTime;
	    private LocalDate departureDate;
	    private double farePerSeat;
	    public int getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(int scheduleId) {
			this.scheduleId = scheduleId;
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
		public int getTotalSeats() {
			return totalSeats;
		}
		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
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
		public LocalDate getDepartureDate() {
			return departureDate;
		}
		public void setDepartureDate(LocalDate departureDate) {
			this.departureDate = departureDate;
		}
		public double getFarePerSeat() {
			return farePerSeat;
		}
		public void setFarePerSeat(double farePerSeat) {
			this.farePerSeat = farePerSeat;
		}
	

			public BusSearchResponseDto(int scheduleId, String busNumber, String busType, int totalSeats, 
					String source, String destination, LocalTime departureTime, LocalTime arrivalTime, 
					LocalDate departureDate, double farePerSeat) {
				this.scheduleId = scheduleId;
				this.busNumber = busNumber;
				this.busType = busType;
				this.totalSeats = totalSeats;
				this.source = source;
				this.destination = destination;
				this.departureTime = departureTime;
				this.arrivalTime = arrivalTime;
				this.departureDate = departureDate;
				this.farePerSeat = farePerSeat;
			}

			// Getters and setters
		}


		
		
	
	
	    


