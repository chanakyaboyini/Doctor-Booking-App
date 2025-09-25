import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import DoctorList from "./DoctorList";
import MyBookings from "./MyBookings";
import BookingUI from "./components/BookingUI"; // ✅ new UI

function App() {
  return (
    <div>
      <h1>Doctor Booking App</h1>

      {/* Navigation */}
      <nav>
        <Link to="/">Doctors</Link> |{" "}
        <Link to="/bookings">My Bookings</Link> |{" "}
        <Link to="/booking-ui">Booking UI</Link>
      </nav>

      {/* Routes */}
      <Routes>
        <Route path="/" element={<DoctorList />} />
        <Route path="/bookings" element={<MyBookings />} />
        <Route path="/booking-ui" element={<BookingUI />} /> {/* ✅ new route */}
      </Routes>
    </div>
  );
}

export default App;