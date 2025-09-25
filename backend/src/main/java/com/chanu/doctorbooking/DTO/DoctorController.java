package com.chanu.doctorbooking.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private static final List<Map<String, Object>> doctors = new ArrayList<>();

    static {
        Map<String, Object> d1 = new HashMap<>();
        d1.put("id", 1);
        d1.put("name", "Dr. Anjali Rao");
        d1.put("specialty", "Cardiology");
        d1.put("clinic", "Healthy Hearts Clinic");

        Map<String, Object> d2 = new HashMap<>();
        d2.put("id", 2);
        d2.put("name", "Dr. Ramesh Gupta");
        d2.put("specialty", "Dermatology");
        d2.put("clinic", "SkinCare Center");

        doctors.add(d1);
        doctors.add(d2);
    }

    @GetMapping
    public List<Map<String, Object>> listDoctors() {
        return doctors;
    }

    @GetMapping("/{id}/slots")
    public List<Map<String, Object>> getSlots(@PathVariable int id) {
        List<Map<String, Object>> slots = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> slot = new HashMap<>();
            slot.put("id", id * 100 + i);
            slot.put("startTime", new Date(System.currentTimeMillis() + i * 3600000));
            slot.put("durationMinutes", 30);
            slot.put("booked", false);
            slots.add(slot);
        }
        return slots;
    }
}