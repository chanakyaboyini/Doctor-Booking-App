package com.chanu.doctorbooking.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private static final List<Map<String, Object>> bookings = new ArrayList<>();

    @PostMapping
    public Map<String, Object> create(@RequestBody Map<String, Object> req) {
        Map<String, Object> booking = new HashMap<>();
        booking.put("id", bookings.size() + 1);
        booking.put("slotId", req.get("slotId"));
        booking.put("patientName", req.get("patientName"));
        booking.put("patientEmail", req.get("patientEmail"));
        booking.put("patientPhone", req.get("patientPhone"));
        booking.put("createdAt", new Date());
        bookings.add(booking);
        return booking;
    }

    @GetMapping
    public List<Map<String, Object>> byEmail(@RequestParam String email) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map<String, Object> b : bookings) {
            if (email.equals(b.get("patientEmail"))) {
                result.add(b);
            }
        }
        return result;
    }
}