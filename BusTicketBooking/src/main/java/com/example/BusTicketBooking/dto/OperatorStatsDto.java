package com.example.BusTicketBooking.dto;

import java.util.List;

public class OperatorStatsDto {
	 private List<String> busNumbers;
	    private List<Long> passengerCounts;
		public List<String> getBusNumbers() {
			return busNumbers;
		}
		public void setBusNumbers(List<String> busNumbers) {
			this.busNumbers = busNumbers;
		}
		public List<Long> getPassengerCounts() {
			return passengerCounts;
		}
		public void setPassengerCounts(List<Long> passengerCounts) {
			this.passengerCounts = passengerCounts;
		}

}
