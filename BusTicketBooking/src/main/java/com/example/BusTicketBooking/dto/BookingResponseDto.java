package com.example.BusTicketBooking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import com.example.BusTicketBooking.model.Booking;


public class BookingResponseDto {

    private int id;
    private String passengerName;
    private String contactNo;
    private String busNumber;
    private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private List<String> seatNumber;
    private double farePerSeat;
    private double totalFare;
    private LocalDateTime bookingTime;
    private String status;

    // ✅ Static converter method
    public static BookingResponseDto fromEntity(Booking booking) {
        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(booking.getId());
        dto.setPassengerName(booking.getPassenger().getName());
        dto.setContactNo(booking.getPassenger().getContact());
        dto.setBusNumber(booking.getSchedule().getBus().getBusNumber());
        dto.setSource(booking.getSchedule().getRoute().getSource());
        dto.setDestination(booking.getSchedule().getRoute().getDestination());
        dto.setDepartureDate(booking.getSchedule().getDepartureDate());
        dto.setDepartureTime(booking.getSchedule().getDepartureTime());
        dto.setSeatNumber(
            booking.getSeats()
                .stream()
                .map(seat -> seat.getSeatNumber())
                .collect(Collectors.toList())
        );
        dto.setFarePerSeat(booking.getSchedule().getFarePerSeat());
        dto.setTotalFare(booking.getTotalFare());
        dto.setBookingTime(booking.getBookingTime());
        dto.setStatus(booking.getStatus().toString());
        return dto;
    }

    // ✅ Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
