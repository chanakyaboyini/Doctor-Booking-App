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

        // Seed slots
        slots.put(1, Arrays.asList(
                Map.of("slotId", 1, "time", "2025-09-26T10:00", "available", true),
                Map.of("slotId", 2, "time", "2025-09-26T11:00", "available", true)
        ));
        slots.put(2, Arrays.asList(
                Map.of("slotId", 3, "time", "2025-09-26T12:00", "available", true),
                Map.of("slotId", 4, "time", "2025-09-26T13:00", "available", true)
        ));
    }

    @GetMapping("/doctors")
    public List<Map<String, Object>> getDoctors() {
        return doctors;
    }

    @GetMapping("/doctors/{id}/slots")
public List<Map<String, Object>> getSlots(@PathVariable int id) {
    System.out.println("Fetching slots for doctor ID: " + id);
    List<Map<String, Object>> doctorSlots = slots.get(id);
    if (doctorSlots == null) {
        System.out.println("No slots found for doctor ID: " + id);
        return new ArrayList<>(); // ✅ Return empty list instead of null
    }
    return doctorSlots;
}

    @PostMapping("/bookings")
    public Map<String, Object> createBooking(@RequestBody Map<String, Object> booking) {
        booking.put("bookingId", UUID.randomUUID().toString());
        bookings.add(booking);
        return booking;
    }

    @GetMapping("/bookings")
    public List<Map<String, Object>> getBookings(@RequestParam String email) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> b : bookings) {
            if (email.equals(b.get("email"))) {
                result.add(b);
            }
        }
        return result;
    }
}