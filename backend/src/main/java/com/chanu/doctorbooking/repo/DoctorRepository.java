package com.chanu.doctorbooking.repo;

import com.chanu.doctorbooking.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {}