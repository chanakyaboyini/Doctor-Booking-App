package com.chanu.doctorbooking.controller;

import com.chanu.doctorbooking.model.Doctor;
import com.chanu.doctorbooking.model.Slot;
import com.chanu.doctorbooking.repo.DoctorRepository;
import com.chanu.doctorbooking.repo.SlotRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
  private final DoctorRepository doctorRepo;
  private final SlotRepository slotRepo;

  public DoctorController(DoctorRepository doctorRepo, SlotRepository slotRepo) {
    this.doctorRepo = doctorRepo;
    this.slotRepo = slotRepo;
  }

  @GetMapping
  public List<Doctor> listDoctors() {
    return doctorRepo.findAll();
  }

  @GetMapping("/{id}/slots")
  public List<Slot> availableSlots(@PathVariable Long id) {
    return slotRepo.findByDoctorIdAndBookedFalse(id);
  }
}