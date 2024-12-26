package com.HMwebsite.HospitalManagementSystem.doclogin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMwebsite.HospitalManagementSystem.doclogin.entity.Appointment;
import com.HMwebsite.HospitalManagementSystem.doclogin.repository.AppointmentRepository;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class AppointmentController {
    
	AppointmentRepository appointmentRepository;

	public AppointmentController(AppointmentRepository appointmentRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
	}
	
	@PostMapping("/appointment")
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		
		return appointmentRepository.save(appointment);
	}
	
	@GetMapping("/appointment")
	public List<Appointment> getAll(){
		return appointmentRepository.findAll();
	}
	
	@DeleteMapping("/appointment/{id}")
	public ResponseEntity<Map<String, Boolean>>deleteAppointment  (@PathVariable long id) throws AttributeNotFoundException{
		
		Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Appointmetn with that id not found"+ id));
        appointmentRepository.delete(appointment);
        Map<String, Boolean>response =new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
		
	}
}
