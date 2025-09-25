import React, { useEffect, useState } from "react";
import axios from "axios";

function MyBookings() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    axios.get("/api/bookings", { params: { email: "chanu@example.com" } })
      .then(res => setBookings(res.data));
  }, []);

  return (
    <div>
      <h2>My Bookings</h2>
      {bookings.map(b => (
        <div key={b.bookingId}>
          <p>Doctor ID: {b.doctorId} | Slot: {b.slotId} | Booking ID: {b.bookingId}</p>
        </div>
      ))}
    </div>
  );
}

export default MyBookings;