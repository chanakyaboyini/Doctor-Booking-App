package com.chanu.doctorbooking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "start_time"}))
public class Slot {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private Doctor doctor;

  @Column(name = "start_time")
  private LocalDateTime startTime;

  private int durationMinutes; // e.g., 30
  private boolean booked = false;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Doctor getDoctor() { return doctor; }
  public void setDoctor(Doctor doctor) { this.doctor = doctor; }
  public LocalDateTime getStartTime() { return startTime; }
  public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
  public int getDurationMinutes() { return durationMinutes; }
  public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
  public boolean isBooked() { return booked; }
  public void setBooked(boolean booked) { this.booked = booked; }
}