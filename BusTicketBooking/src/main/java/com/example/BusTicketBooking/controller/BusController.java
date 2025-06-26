package com.example.BusTicketBooking.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.dto.BusDto;
import com.example.BusTicketBooking.dto.BusSearchResponseDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.service.BusService;

@RestController
@RequestMapping("/api/bus")
@CrossOrigin(origins = "http://localhost:5173")
public class BusController {

	@Autowired
	private BusService busService;

	// this is the api for add bus
	@PostMapping("/add/{routeId}")
	public ResponseEntity<?> addBus(@RequestBody Bus bus, Principal principal, @PathVariable int routeId) {
		String operatorName = principal.getName();
		Bus savedBus = busService.addBus(bus, operatorName, routeId);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
	}

	// if i implement admin then i use this
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllBuses() {
		return ResponseEntity.ok(busService.getAllBuses());
	}

	// get bus by id
	@GetMapping("get/{id}")
	public ResponseEntity<?> getBusById(@PathVariable int id) {
		return ResponseEntity.ok(busService.getBusById(id));
	}

	// for delete
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteBus(@PathVariable int id) {
		busService.deleteBus(id);
		return ResponseEntity.ok("Bus deleted successfully");
	}

	// for update
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateBus(@PathVariable int id, @RequestBody BusDto dto) {
		busService.updateBusFromDto(id, dto);
		return ResponseEntity.ok("Bus updated successfully");
	}

	// for get bus by operator
	@GetMapping("/get-by-operator")
	public ResponseEntity<?> getBusesByOperator(Principal principal) {
		String username = principal.getName(); // taken from JWT login
		List<BusDto> buses = busService.getBusesByOperator(username);
		return ResponseEntity.ok(buses);
	}
	//for searching bus from passenger side
	@GetMapping("/search")
	public List<BusSearchResponseDto> searchBuses(@RequestParam String source, @RequestParam String destination,
			@RequestParam LocalDate date) {
		return busService.searchBuses(source, destination, date);
	}

}
