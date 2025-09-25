package com.chanu.doctorbooking.config;

import com.chanu.doctorbooking.model.Doctor;
import com.chanu.doctorbooking.model.Slot;
import com.chanu.doctorbooking.repo.DoctorRepository;
import com.chanu.doctorbooking.repo.SlotRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@Configuration
public class DataLoader {
  @Bean
  CommandLineRunner loadData(DoctorRepository doctorRepo, SlotRepository slotRepo) {
    return args -> {
      if (doctorRepo.count() > 0) return;

      Doctor d1 = new Doctor();
      d1.setName("Dr. Anjali Rao");
      d1.setSpecialty("Cardiology");
      d1.setClinic("Healthy Hearts Clinic");

      Doctor d2 = new Doctor();
      d2.setName("Dr. Ramesh Gupta");
      d2.setSpecialty("Dermatology");
      d2.setClinic("SkinCare Center");

      Doctor d3 = new Doctor();
      d3.setName("Dr. Saira Khan");
      d3.setSpecialty("Pediatrics");
      d3.setClinic("Little Care Hospital");

      doctorRepo.saveAll(List.of(d1, d2, d3));

      for (Doctor doc : doctorRepo.findAll()) {
        LocalDateTime base = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0).withNano(0);
        IntStream.range(0, 8).forEach(i -> {
          Slot s = new Slot();
          s.setDoctor(doc);
          s.setStartTime(base.plusDays(1).plusMinutes(i * 30));
          s.setDurationMinutes(30);
          s.setBooked(false);
          slotRepo.save(s);
        });
      }
    };
  }
}