package com.chanu.doctorbooking.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "slot_id")
  private Slot slot;

  private String patientName;
  private String patientEmail;
  private String patientPhone;
  private LocalDateTime createdAt;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Slot getSlot() { return slot; }
  public void setSlot(Slot slot) { this.slot = slot; }
  public String getPatientName() { return patientName; }
  public void setPatientName(String patientName) { this.patientName = patientName; }
  public String getPatientEmail() { return patientEmail; }
  public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }
  public String getPatientPhone() { return patientPhone; }
  public void setPatientPhone(String patientPhone) { this.patientPhone = patientPhone; }
  public LocalDateTime getCreatedAt() { return createdAt; }
  public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}