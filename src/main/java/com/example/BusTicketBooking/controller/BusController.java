package com.example.BusTicketBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.service.BusService;

@RestController
@RequestMapping("/api/bus")
public class BusController {
	
	  @Autowired
	    private BusService busService;

	    @PostMapping("/add/{operatorId}/{routeId}")
	    public ResponseEntity<?> addBus(@RequestBody Bus bus,
	                                    @PathVariable int operatorId,
	                                    @PathVariable int routeId) {
	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(busService.addBus(bus, operatorId, routeId));
	    }

	    @GetMapping("/get-all")
	    public ResponseEntity<?> getAllBuses() {
	        return ResponseEntity.ok(busService.getAllBuses());
	    }
	    
	    @GetMapping("get/{id}")
	    public ResponseEntity<?> getBusById(@PathVariable int id) {
	        return ResponseEntity.ok(busService.getBusById(id));
	    }

	    @DeleteMapping("delete/{id}")
	    public ResponseEntity<?> deleteBus(@PathVariable int id) {
	        busService.deleteBus(id);
	        return ResponseEntity.ok("Bus deleted successfully");
	    }

	    @PutMapping("update/{id}")
	    public ResponseEntity<?> updateBus(@PathVariable int id, @RequestBody Bus updatedBus) {
	        return ResponseEntity.ok(busService.updateBus(id, updatedBus));
	    }

	    

}
