package com.example.BusTicketBooking.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.BusTicketBooking.model.Schedule;
import com.example.BusTicketBooking.service.ScheduleService;

@RestController
@RequestMapping("/api/schedule")
@CrossOrigin(origins = "http://localhost:5173")
public class ScheduleController {

   
	@Autowired
	private ScheduleService scheduleService;


	
	
	@PostMapping("/add/{busId}/{routeId}")
	public ResponseEntity<?> addSchedule(@RequestBody Schedule schedule,
										@PathVariable int busId,
										@PathVariable int routeId){
		return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.addSchedule(schedule,busId,routeId));
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllSchedules(){
		return ResponseEntity.ok(scheduleService.getAllSchedules());
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getScheduleById(@PathVariable int id){
		return ResponseEntity.ok(scheduleService.getScheduleById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteSchedule(@PathVariable int id) {
	        scheduleService.deleteSchedule(id);
	        return ResponseEntity.ok("Schedule deleted successfully");
	    }

	@PutMapping("/update/{id}")
	    public ResponseEntity<?> updateSchedule(@PathVariable int id,
	                                            @RequestBody Schedule schedule) {
	        return ResponseEntity.ok(scheduleService.updateSchedule(id, schedule));
	    }

}
