import React, { useState } from 'react';
import { Routes, Route, Link, useNavigate } from 'react-router-dom';
import DoctorList from './DoctorList';
import DoctorDetail from './DoctorDetail';
import MyBookings from './MyBookings';

export default function App() {
  const [email, setEmail] = useState('');
  const nav = useNavigate();

  return (
    <div style={{ fontFamily: 'system-ui', maxWidth: 900, margin: '0 auto', padding: 16 }}>
      <header style={{ display: 'flex', gap: 16, alignItems: 'center', marginBottom: 16 }}>
        <h2 style={{ margin: 0 }}>Doctor Booking</h2>
        <Link to="/">Doctors</Link>
        <Link to="/my-bookings">My Bookings</Link>
        <div style={{ marginLeft: 'auto' }}>
          <input
            placeholder="Your email (for bookings)"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            style={{ padding: 8 }}
          />
          <button onClick={() => nav('/my-bookings', { state: { email } })} style={{ marginLeft: 8 }}>
            View
          </button>
        </div>
      </header>
      <Routes>
        <Route path="/" element={<DoctorList />} />
        <Route path="/doctor/:id" element={<DoctorDetail />} />
        <Route path="/my-bookings" element={<MyBookings />} />
      </Routes>
    </div>
  );
}