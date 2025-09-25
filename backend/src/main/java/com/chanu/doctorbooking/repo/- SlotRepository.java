package com.chanu.doctorbooking.repo;

import com.chanu.doctorbooking.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {
  List<Slot> findByDoctorIdAndBookedFalse(Long doctorId);
}