import React, { useEffect, useState } from "react";
import axios from "axios";

function DoctorList() {
  const [doctors, setDoctors] = useState([]);
  const [slots, setSlots] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    axios.get("/api/doctors")
      .then(res => setDoctors(res.data))
      .catch(err => {
        console.error("Error fetching doctors:", err);
        alert("Failed to load doctors");
      });
  }, []);

  const viewSlots = (doctorId) => {
    console.log("Fetching slots for doctor:", doctorId);
    setLoading(true);
    setSelectedDoctor(doctorId);
    axios.get(`/api/doctors/${doctorId}/slots`)
      .then(res => {
        console.log("Slots received:", res.data);
        setSlots(res.data);
      })
      .catch(err => {
        console.error("Error fetching slots:", err);
        alert("Failed to load slots");
      })
      .finally(() => setLoading(false));
  };

  const bookSlot = (slotId) => {
  axios.post("/api/bookings", {
    doctorId: selectedDoctor,
    slotId,
    patientName: "Chanu",
    email: "chanu@example.com"
  })
    .then(res => {
      alert("Booking Confirmed! ID: " + res.data.bookingId);
    })
    .catch(err => {
      console.error("Error booking slot:", err);
      alert("Booking failed");
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

      {loading && <p>Loading slots...</p>}

      {slots.length > 0 && (
  <div>
    <h3>Available Slots</h3>
    {slots.map(s => (
  <div key={s.slotId || s.id}>
    <span>
      {s.time || new Date(s.startTime).toLocaleString()}
    </span>
    {(s.available !== undefined ? s.available : !s.booked) && (
      <button onClick={() => bookSlot(s.slotId || s.id)}>Book</button>
    )}
  </div>
))}



      {slots.length === 0 && selectedDoctor && !loading && (
        <p>No slots available for this doctor.</p>
      )}

      {/* Debug preview */}
      <pre>{JSON.stringify(slots, null, 2)}</pre>
    </div>
  );
}

export default DoctorList;