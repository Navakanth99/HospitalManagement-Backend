package com.HMwebsite.HospitalManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HMwebsite.HospitalManagementSystem.Entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
