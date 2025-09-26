import React, { useState } from "react";
import "./BookingUI.css"; // 👈 we'll style separately

function BookingUI() {
  const [selectedDate, setSelectedDate] = useState("");
  const [reason, setReason] = useState("");
  const [specialist, setSpecialist] = useState("");
  const [time, setTime] = useState("");

  const doctors = [
    { id: 1, name: "Dr. Ramesh", specialization: "Cardiologist" },
    { id: 2, name: "Dr. Priya", specialization: "Dermatologist" },
    { id: 3, name: "Dr. Arjun", specialization: "Neurologist" }
  ];

  const handleSubmit = (e) => {
    e.preventDefault();
    alert(`Booking confirmed with ${specialist} on ${selectedDate} at ${time}`);
  };

  return (
    <div className="booking-container">
      {/* Left: Doctors */}
      <div className="doctor-list">
        <h3>Available Doctors</h3>
        {doctors.map(d => (
          <div key={d.id} className="doctor-card">
            <img
              src={`https://via.placeholder.com/80?text=${d.name.split(" ")[1]}`}
              alt={d.name}
              className="doctor-avatar"
            />
            <div>
              <p><strong>{d.name}</strong></p>
              <p>{d.specialization}</p>
            </div>
          </div>
        ))}
      </div>

      {/* Middle: Calendar */}
      <div className="calendar">
        <h3>Calendar</h3>
        <input
          type="date"
          value={selectedDate}
          onChange={(e) => setSelectedDate(e.target.value)}
        />
      </div>

      {/* Right: Booking Form */}
      <div className="booking-form">
        <h3>Booking</h3>
        <form onSubmit={handleSubmit}>
          <label>Reason for Appointment</label>
          <input
            type="text"
            value={reason}
            onChange={(e) => setReason(e.target.value)}
            placeholder="Enter reason"
          />

          <label>Select Specialist</label>
          <select
            value={specialist}
            onChange={(e) => setSpecialist(e.target.value)}
          >
            <option value="">-- Select --</option>
            {doctors.map(d => (
              <option key={d.id} value={d.name}>
                {d.name} ({d.specialization})
              </option>
            ))}
          </select>

          <label>Select Time</label>
          <input
            type="time"
            value={time}
            onChange={(e) => setTime(e.target.value)}
          />

          <div className="form-buttons">
            <button type="button" className="cancel-btn">Cancel</button>
            <button type="submit" className="submit-btn">Start/Sign Form</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default BookingUI;