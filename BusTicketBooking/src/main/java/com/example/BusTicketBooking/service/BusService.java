package com.example.BusTicketBooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BusTicketBooking.dto.BusDto;
import com.example.BusTicketBooking.dto.BusSearchResponseDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.model.Route;
import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.repository.BusRepository;
import com.example.BusTicketBooking.repository.OperatorRepository;
import com.example.BusTicketBooking.repository.RouteRepository;
import com.example.BusTicketBooking.repository.ScheduleRepository;

@Service
public class BusService {

	private final BusRepository busRepository;
	private final OperatorRepository operatorRepository;
	private final RouteRepository routeRepository;
	private ScheduleRepository scheduleRepository;
	@Autowired
	private BusDto busDto;

	public BusService(BusRepository busRepository, OperatorRepository operatorRepository,
			RouteRepository routeRepository, ScheduleRepository scheduleRepository) {
		super();
		this.busRepository = busRepository;
		this.operatorRepository = operatorRepository;
		this.routeRepository = routeRepository;
		this.scheduleRepository = scheduleRepository;
	}

	public Bus addBus(Bus bus, String operatorName, int routeId) {
		Operator operator = operatorRepository.findOperatorByUsername(operatorName)
				.orElseThrow(() -> new RuntimeException("Invalid Operator Name"));

		Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Invalid Route ID"));

		bus.setOperator(operator);
		bus.setRoute(route);

		return busRepository.save(bus);
	}

	public List<BusDto> getAllBuses() {
		List<Bus> list = busRepository.findAll();
		return busDto.convertBusIntoDto(list);
	}

	public void deleteBus(int id) {
		busRepository.deleteById(id);
	}

	public Bus updateBus(int id, Bus updatedBus) {
		Bus dbBus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Bus ID"));

		if (updatedBus.getRoute() != null && updatedBus.getRoute().getId() != null) {
			// Fetch route from DB by route ID to ensure valid relation
			Route route = routeRepository.findById(updatedBus.getRoute().getId())
					.orElseThrow(() -> new RuntimeException("Invalid Route ID"));
			dbBus.setRoute(route);
		}

		if (updatedBus.getBusNumber() != null)
			dbBus.setBusNumber(updatedBus.getBusNumber());
		if (updatedBus.getBusType() != null)
			dbBus.setBusType(updatedBus.getBusType());
		if (updatedBus.getTotalSeats() != 0)
			dbBus.setTotalSeats(updatedBus.getTotalSeats());

		return busRepository.save(dbBus);
	}

	public BusDto getBusById(int id) {
		Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Bus ID"));
		// Wrap the single Bus in a list, convert, then return the first item
		return busDto.convertBusIntoDto(List.of(bus)).get(0);
	}

	public List<BusDto> getBusesByOperator(String username) {
		List<Bus> buses = busRepository.getByOperatorname(username);
		return busDto.convertBusIntoDto(buses);
	}

	public List<BusSearchResponseDto> searchBuses(String source, String destination, LocalDate date) {
		List<Schedule> schedules = scheduleRepository.searchBuses(source, destination, date);

		// source = source.trim();
		// destination = destination.trim();

		return schedules.stream().map(s -> new BusSearchResponseDto(s.getId(), // ✅ Correct scheduleId
				s.getBus().getBusNumber(), s.getBus().getBusType(), s.getBus().getTotalSeats(),
				s.getBus().getRoute().getSource(), s.getBus().getRoute().getDestination(), s.getDepartureTime(),
				s.getArrivalTime(), s.getDepartureDate(), s.getFarePerSeat())).collect(Collectors.toList());
	}

	public void updateBusFromDto(int id, BusDto dto) {
		Bus dbBus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Bus ID"));

		if (dto.getBusNumber() != null)
			dbBus.setBusNumber(dto.getBusNumber());

		if (dto.getBusType() != null)
			dbBus.setBusType(dto.getBusType());

		if (dto.getTotalSeats() != 0)
			dbBus.setTotalSeats(dto.getTotalSeats());
		if (dto.getRouteId() != 0) {
			Route route = routeRepository.findById(dto.getRouteId())
					.orElseThrow(() -> new RuntimeException("Invalid Route ID"));
			dbBus.setRoute(route);

			busRepository.save(dbBus);
		}

	}

}
