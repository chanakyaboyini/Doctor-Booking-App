import React from "react";

export default function DoctorCard({ doctor, onBook }) {
  return (
    <div className="doctor-card">
      <img src={doctor.photo} alt={doctor.name} />
      <h3>{doctor.name}</h3>
      <p>{doctor.specialization}</p>
      <button onClick={() => onBook(doctor)}>Book Appointment</button>
    </div>
  );
}