package com.example.BusTicketBooking.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.example.BusTicketBooking.model.Booking;



@Component
public class BookingDto {

	    private int id;
	    private String passengerName;
	    private String contactNo;
	    private String busNumber;
	    private String busType;
	    private int scheduleId;
	    private String source;
	    private String destination;
	    List<String> seatNumber;
	    private double farePerSeat;
	    private double totalFare;
	    private LocalDateTime bookingTime;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPassengerName() {
			return passengerName;
		}
		public void setPassengerName(String passengerName) {
			this.passengerName = passengerName;
		}
		public String getContactNo() {
			return contactNo;
		}
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
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
		
		public List<String> getSeatNumber() {
			return seatNumber;
		}
		public void setSeatNumber(List<String> seatNumber) {
			this.seatNumber = seatNumber;
		}
		public double getFarePerSeat() {
			return farePerSeat;
		}
		public void setFarePerSeat(double farePerSeat) {
			this.farePerSeat = farePerSeat;
		}
		public double getTotalFare() {
			return totalFare;
		}
		public void setTotalFare(double totalFare) {
			this.totalFare = totalFare;
		}
		public LocalDateTime getBookingTime() {
			return bookingTime;
		}
		public void setBookingTime(LocalDateTime bookingTime) {
			this.bookingTime = bookingTime;
		}
	    
		 public int getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(int scheduleId) {
			this.scheduleId = scheduleId;
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
		// Convert one Booking object
		public BookingDto convertBookingIntoDto(Booking booking) {
		    BookingDto dto = new BookingDto();
		    dto.setId(booking.getId());
		    dto.setPassengerName(booking.getPassenger().getName());
		    dto.setContactNo(booking.getPassenger().getContact());
		    dto.setBusType(booking.getSchedule().getBus().getBusType());
		    dto.setBusNumber(booking.getSchedule().getBus().getBusNumber());
		    dto.setSource(booking.getSchedule().getBus().getRoute().getSource());
		    dto.setDestination(booking.getSchedule().getBus().getRoute().getDestination());

		    List<String> seatNumber = booking.getSeats().stream()
		        .map(seat -> seat.getSeatNumber())
		        .collect(java.util.stream.Collectors.toList());

		    dto.setSeatNumber(seatNumber);
		    dto.setFarePerSeat(booking.getSchedule().getFarePerSeat());
		    dto.setTotalFare(booking.getTotalFare());
		    dto.setScheduleId(booking.getSchedule().getId());
		    dto.setBookingTime(booking.getBookingTime());

		    return dto;
		}

		// Already present - Convert List<Booking> to List<BookingDto>
		public List<BookingDto> convertBookingIntoDto(List<Booking> list) {
		    List<BookingDto> listDto = new ArrayList<>();
		    list.stream().forEach(booking -> {
		        listDto.add(convertBookingIntoDto(booking)); // Reuse single converter
		    });
		    return listDto;
		}

}
