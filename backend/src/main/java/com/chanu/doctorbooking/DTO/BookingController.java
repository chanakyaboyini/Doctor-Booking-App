package com.chanu.doctorbooking.controller;

import com.chanu.doctorbooking.dto.CreateBookingRequest;
import com.chanu.doctorbooking.model.Booking;
import com.chanu.doctorbooking.model.Slot;
import com.chanu.doctorbooking.repo.BookingRepository;
import com.chanu.doctorbooking.repo.SlotRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private final BookingRepository bookingRepo;
  private final SlotRepository slotRepo;

  public BookingController(BookingRepository bookingRepo, SlotRepository slotRepo) {
    this.bookingRepo = bookingRepo;
    this.slotRepo = slotRepo;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> create(@RequestBody CreateBookingRequest req) {
    Slot slot = slotRepo.findById(req.slotId).orElse(null);
    if (slot == null || slot.isBooked()) {
      return ResponseEntity.badRequest().body("Slot not available");
    }

    Booking booking = new Booking();
    booking.setSlot(slot);
    booking.setPatientName(req.patientName);
    booking.setPatientEmail(req.patientEmail);
    booking.setPatientPhone(req.patientPhone);
    booking.setCreatedAt(LocalDateTime.now());

    slot.setBooked(true);
    slotRepo.save(slot);
    bookingRepo.save(booking);

    return ResponseEntity.ok(booking);
  }

  @GetMapping
  public List<Booking> byEmail(@RequestParam String email) {
    return bookingRepo.findByPatientEmailOrderByCreatedAtDesc(email);
  }
}