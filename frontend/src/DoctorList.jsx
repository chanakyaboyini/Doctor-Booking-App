import React, { useEffect, useState } from "react";
import axios from "axios";

function DoctorList() {
  const [doctors, setDoctors] = useState([]);
  const [slots, setSlots] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState(null);

  useEffect(() => {
    axios.get("/api/doctors").then(res => setDoctors(res.data));
  }, []);

  const viewSlots = (doctorId) => {
    setSelectedDoctor(doctorId);
    axios.get(`/api/doctors/${doctorId}/slots`).then(res => setSlots(res.data));
  };

  const bookSlot = (slotId) => {
    axios.post("/api/bookings", {
      doctorId: selectedDoctor,
      slotId,
      patientName: "Chanu",
      email: "chanu@example.com"
    }).then(res => {
      alert("Booking Confirmed! ID: " + res.data.bookingId);
    });
  };

  return (
    <div>
      <h2>Doctors</h2>
      {doctors.map(d => (
        <div key={d.id}>
          <p>{d.name} - {d.specialization} (₹{d.fee})</p>
          <button onClick={() => viewSlots(d.id)}>View Slots</button>
        </div>
      ))}

      {slots.length > 0 && (
        <div>
          <h3>Available Slots</h3>
          {slots.map(s => (
            <div key={s.slotId}>
              <span>{s.time}</span>
              {s.available && <button onClick={() => bookSlot(s.slotId)}>Book</button>}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default DoctorList;