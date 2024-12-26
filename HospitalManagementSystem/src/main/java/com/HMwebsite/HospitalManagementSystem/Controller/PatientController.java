package com.HMwebsite.HospitalManagementSystem.Controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMwebsite.HospitalManagementSystem.Entity.Patient;
import com.HMwebsite.HospitalManagementSystem.Repository.PatientRepository;
import com.HMwebsite.HospitalManagementSystem.doclogin.entity.Appointment;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class PatientController {
	
	private PatientRepository patientRepository;

	public PatientController(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
    
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient) {
		
		return patientRepository.save(patient);
	}
	
	@GetMapping("/patients")
	public List<Patient> getAll(){
		return patientRepository.findAll();
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable long id) throws AttributeNotFoundException{
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Patient Not Found with that id "+ id));
		
		return ResponseEntity.ok(patient);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String, Boolean>>deleteAppointment  (@PathVariable long id) throws AttributeNotFoundException{
		
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Appointmetn with that id not found"+ id));
		patientRepository.delete(patient);
        Map<String, Boolean>response =new HashMap<String, Boolean>();
        response.put("Deleted", Boolean.TRUE);
        
        return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatPatienteById(@PathVariable long id, @RequestBody Patient patientDetails) throws AttributeNotFoundException{
		
		Patient patient = patientRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Appointmetn with that id not found"+ id));
		
		patient.setAge(patientDetails.getAge());
		patient.setName(patientDetails.getName());
		patient.setBlood(patientDetails.getBlood());
		patient.setDose(patientDetails.getDose());
		patient.setFees(patientDetails.getFees());
		patient.setPrescription(patientDetails.getPrescription());
		patient.setUrgency(patientDetails.getUrgency());
		
		Patient savedPatient =patientRepository.save(patient);
		
		return ResponseEntity.ok(savedPatient);
		
		
	}
}
