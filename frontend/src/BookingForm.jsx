import React, { useState } from 'react';
import { api } from './api';

export default function BookingForm({ slot, onDone }) {
  const [patientName, setPatientName] = useState('');
  const [patientEmail, setPatientEmail] = useState('');
  const [patientPhone, setPatientPhone] = useState('');
  const [status, setStatus] = useState('');

  const submit = async () => {
    setStatus('Booking...');
    try {
      const res = await api.post('/bookings', {
        slotId: slot.id, patientName, patientEmail, patientPhone
      });
      setStatus('Booked successfully!');
      onDone?.();
    } catch (e) {
      setStatus(e.response?.data || 'Failed to book');
    }
  };

  return (
    <div>
      <h4>Book slot: {new Date(slot.startTime).toLocaleString()}</h4>
      <div style={{ display: 'grid', gap: 8, maxWidth: 400 }}>
        <input placeholder="Full name" value={patientName} onChange={e => setPatientName(e.target.value)} />
        <input placeholder="Email" value={patientEmail} onChange={e => setPatientEmail(e.target.value)} />
        <input placeholder="Phone" value={patientPhone} onChange={e => setPatientPhone(e.target.value)} />
        <button onClick={submit}>Confirm booking</button>
        <div>{status}</div>
      </div>
    </div>
  );
}
