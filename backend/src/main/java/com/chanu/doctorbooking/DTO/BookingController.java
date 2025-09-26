package com.doctorbooking.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class BookingController {

    // Dummy doctors
    private List<Map<String, Object>> doctors = new ArrayList<>();
    // Dummy slots
    private Map<Integer, List<Map<String, Object>>> slots = new HashMap<>();
    // Dummy bookings
    private List<Map<String, Object>> bookings = new ArrayList<>();

    public BookingController() {
        // Seed doctors
        Map<String, Object> d1 = new HashMap<>();
        d1.put("id", 1);
        d1.put("name", "Dr. Ramesh");
        d1.put("specialization", "Cardiologist");
        d1.put("fee", 500);
        doctors.add(d1);

        Map<String, Object> d2 = new HashMap<>();
        d2.put("id", 2);
        d2.put("name", "Dr. Priya");
        d2.put("specialization", "Dermatologist");
        d2.put("fee", 400);
        doctors.add(d2);

        // Seed slots for doctor 1
        List<Map<String, Object>> doctor1Slots = new ArrayList<>();
        doctor1Slots.add(Map.of("slotId", 1, "time", "2025-09-26T10:00", "available", true));
        doctor1Slots.add(Map.of("slotId", 2, "time", "2025-09-26T11:00", "available", true));
        doctor1Slots.add(Map.of("id", 200, "startTime", "2025-09-25T17:43:56.063+00:00", "durationMinutes", 30, "booked", false));
        slots.put(1, doctor1Slots);

        // Seed slots for doctor 2
        List<Map<String, Object>> doctor2Slots = new ArrayList<>();
        doctor2Slots.add(Map.of("slotId", 3, "time", "2025-09-26T12:00", "available", true));
        doctor2Slots.add(Map.of("slotId", 4, "time", "2025-09-26T13:00", "available", true));
        slots.put(2, doctor2Slots);
    }

    @GetMapping("/doctors")
    public List<Map<String, Object>> getDoctors() {
        System.out.println("Returning list of doctors");
        return doctors;
    }

    @GetMapping("/doctors/{id}/slots")
    public List<Map<String, Object>> getSlots(@PathVariable("id") int doctorId) {
        System.out.println("Fetching slots for doctor ID: " + doctorId);
        List<Map<String, Object>> doctorSlots = slots.get(doctorId);
        if (doctorSlots == null) {
            return new ArrayList<>();
        }

        // Normalize to slotId/time/available
        List<Map<String, Object>> normalized = new ArrayList<>();
        for (Map<String, Object> s : doctorSlots) {
            Map<String, Object> slot = new HashMap<>();
            if (s.containsKey("id")) {
                slot.put("slotId", s.get("id"));
                slot.put("time", s.get("startTime"));
                slot.put("available", !(Boolean) s.get("booked"));
            } else {
                slot.putAll(s);
            }
            normalized.add(slot);
        }
        return normalized;
    }

    @PostMapping("/bookings")
    public Map<String, Object> createBooking(@RequestBody Map<String, Object> booking) {
        String bookingId = UUID.randomUUID().toString();
        booking.put("bookingId", bookingId);
        bookings.add(booking);
        System.out.println("Booking created: " + bookingId);
        return booking;
    }

    @GetMapping("/bookings")
    public List<Map<String, Object>> getBookings(@RequestParam String email) {
        System.out.println("Fetching bookings for email: " + email);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> b : bookings) {
            if (email.equals(b.get("email"))) {
                result.add(b);
            }
        }
        return result;
    }
}