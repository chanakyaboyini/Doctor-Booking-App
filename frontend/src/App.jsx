import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import DoctorList from "./DoctorList";
import MyBookings from "./MyBookings";

function App() {
  return (
    <div>
      <h1>Doctor Booking App</h1>
      <nav>
        <Link to="/">Doctors</Link> | <Link to="/bookings">My Bookings</Link>
      </nav>
      <Routes>
        <Route path="/" element={<DoctorList />} />
        <Route path="/bookings" element={<MyBookings />} />
      </Routes>
    </div>
  );
}

export default App;