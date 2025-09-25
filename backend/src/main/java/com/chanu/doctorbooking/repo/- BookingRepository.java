package com.chanu.doctorbooking.repo;

import com.chanu.doctorbooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByPatientEmailOrderByCreatedAtDesc(String patientEmail);
}