package com.example.BusTicketBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.BusDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.repository.BusRepository;
import com.example.BusTicketBooking.repository.OperatorRepository;
import com.example.BusTicketBooking.repository.RouteRepository;
@Service
public class BusService {
	
	    private final BusRepository busRepository;
	    private final OperatorRepository operatorRepository;
	    private final RouteRepository routeRepository;
	    @Autowired
		private BusDto busDto;

	    public BusService(BusRepository busRepository, OperatorRepository operatorRepository,
	                      RouteRepository routeRepository) {
	        this.busRepository = busRepository;
	        this.operatorRepository = operatorRepository;
	        this.routeRepository = routeRepository;
	    }

	    public Object addBus(Bus bus, int operatorId, int routeId) {
		    Operator operator = operatorRepository.findById(operatorId)
	                .orElseThrow(() -> new RuntimeException("Invalid Operator ID"));
	        Route route = routeRepository.findById(routeId)
	                .orElseThrow(() -> new RuntimeException("Invalid Route ID"));

	        bus.setOperator(operator);
	        bus.setRoute(route);

	        return busRepository.save(bus);
		
	}
	     public List<BusDto> getAllBuses() {
	        List<Bus> list=busRepository.findAll();
	        return busDto.convertBusIntoDto(list);
	      }

	     public void deleteBus(int id) {
	        busRepository.deleteById(id);
	      }

	     public Bus updateBus(int id, Bus updatedBus) {
	        Bus dbBus = busRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Invalid Bus ID"));

	        if (updatedBus.getBusNumber() != null)
	            dbBus.setBusNumber(updatedBus.getBusNumber());
	        if (updatedBus.getBusType() != null)
	            dbBus.setBusType(updatedBus.getBusType());
	        if (updatedBus.getTotalSeats() != 0)
	            dbBus.setTotalSeats(updatedBus.getTotalSeats());

	        return busRepository.save(dbBus);
	     }

	     

	     public BusDto getBusById(int id) {
	         Bus bus = busRepository.findById(id)
	                 .orElseThrow(() -> new RuntimeException("Invalid Bus ID"));
	         // Wrap the single Bus in a list, convert, then return the first item
	         return busDto.convertBusIntoDto(List.of(bus)).get(0);
	     }


}
