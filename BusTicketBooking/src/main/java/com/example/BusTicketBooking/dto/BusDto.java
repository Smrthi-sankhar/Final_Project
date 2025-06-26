package com.example.BusTicketBooking.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.BusTicketBooking.model.Bus;

@Component
public class BusDto {
		private int id;
	    private String busNumber;
	    private String busType;
	    private int totalSeats;
	    private int operatorId;
	    private String operatorName;
	    private String companyName;
	    private int routeId;
	    private String source;
	    private String destination;
	    private String contactNo;
	  


		public int getOperatorId() {
			return operatorId;
		}
		public void setOperatorId(int operatorId) {
			this.operatorId = operatorId;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public int getRouteId() {
			return routeId;
		}
		public void setRouteId(int routeId) {
			this.routeId = routeId;
		}
		public String getContactNo() {
			return contactNo;
		}
		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
		public String getOperatorName() {
			return operatorName;
		}
		public void setOperatorName(String operatorName) {
			this.operatorName = operatorName;
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
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
	    
	    public List<BusDto> convertBusIntoDto(List<Bus> list) {
	        List<BusDto> listDto = new ArrayList<>();
	        list.stream().forEach(bus -> {
	        	BusDto dto = new BusDto();
	            dto.setId(bus.getId());
	            dto.setBusNumber(bus.getBusNumber());
	            dto.setBusType(bus.getBusType());
	            dto.setTotalSeats(bus.getTotalSeats());
	            dto.setOperatorId(bus.getOperator().getId());
	            dto.setOperatorName(bus.getOperator().getName());
	            dto.setCompanyName(bus.getOperator().getCompanyName());
	            dto.setContactNo(bus.getOperator().getContact());
	            dto.setRouteId(bus.getRoute().getId());
	            dto.setSource(bus.getRoute().getSource());
	            dto.setDestination(bus.getRoute().getDestination());
	       
	            listDto.add(dto);
	        });

	        return listDto;
	    }

}
