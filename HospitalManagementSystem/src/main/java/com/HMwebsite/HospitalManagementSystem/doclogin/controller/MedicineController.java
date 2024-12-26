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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HMwebsite.HospitalManagementSystem.doclogin.entity.Medicine;
import com.HMwebsite.HospitalManagementSystem.doclogin.repository.MedicineRepository;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class MedicineController {
    
	MedicineRepository medicineRepository;

	public MedicineController(MedicineRepository medicineRepository) {
		super();
		this.medicineRepository = medicineRepository;
	}
	
	@PostMapping("/medicine")
	public Medicine createMedicine(@RequestBody Medicine medicine) {
		
		return medicineRepository.save(medicine);
	}
	
	@GetMapping("/medicine")
	public List<Medicine> getAll(){
		return medicineRepository.findAll();
	}
	
	@GetMapping("/medicine/{id}")
	public ResponseEntity<Medicine> getMedicineById(@PathVariable long id) throws AttributeNotFoundException{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Medicine Not Found With that id "));
		
		return ResponseEntity.ok(medicine);
	}
	
	@PutMapping("/medicine/{id}")
	public ResponseEntity<Medicine> updateMedcine( @PathVariable long id, @RequestBody Medicine medicineDetails) throws AttributeNotFoundException{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Medicine Not Found With that id "+ id));
		medicine.setDrugName(medicineDetails.getDrugName());
		medicine.setId(medicineDetails.getId());
		
		medicineRepository.save(medicine);
		
		return ResponseEntity.ok(medicine);
	}
	
	@DeleteMapping("/medicine/{id}")
	public ResponseEntity<Map<String, Boolean>> delete(@PathVariable long id) throws AttributeNotFoundException{
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException("Medicine Not Found With that id "+ id));
		medicineRepository.delete(medicine);
		
		Map<String, Boolean>response =new HashMap<String, Boolean>();
		response.put("Deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
}
