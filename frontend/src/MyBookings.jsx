import React, { useEffect, useState } from 'react';
import { api } from './api';
import { useLocation } from 'react-router-dom';

export default function MyBookings() {
  const { state } = useLocation();
  const [email, setEmail] = useState(state?.email || '');
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    if (!email) return;
    api.get(`/bookings`, { params: { email } }).then(res => setBookings(res.data));
  }, [email]);

  return (
    <div>
      <h3>My Bookings</h3>
      <input placeholder="Enter email" value={email} onChange={e => setEmail(e.target.value)} />
      <button onClick={() => {
        if (email) api.get(`/bookings`, { params: { email } }).then(res => setBookings(res.data));
      }} style={{ marginLeft: 8 }}>
        Refresh
      </button>
      <ul style={{ marginTop: 12 }}>
        {bookings.map(b => (
          <li key={b.id}>
            {new Date(b.slot.startTime).toLocaleString()} with {b.slot.doctor.name}
          </li>
        ))}
      </ul>
    </div>
  );
}