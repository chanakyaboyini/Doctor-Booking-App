import React, { useEffect, useState } from 'react';
import { api } from './api';
import { useParams } from 'react-router-dom';
import BookingForm from './BookingForm';

export default function DoctorDetail() {
  const { id } = useParams();
  const [slots, setSlots] = useState([]);
  const [selected, setSelected] = useState(null);

  useEffect(() => {
    api.get(`/doctors/${id}/slots`).then(res => setSlots(res.data));
  }, [id]);

  return (
    <div>
      <h3>Available slots</h3>
      <ul>
        {slots.map(s => (
          <li key={s.id} style={{ marginBottom: 8 }}>
            {new Date(s.startTime).toLocaleString()} ({s.durationMinutes} min)
            <button onClick={() => setSelected(s)} style={{ marginLeft: 8 }}>
              Book
            </button>
          </li>
        ))}
      </ul>
      {selected && (
        <div style={{ borderTop: '1px solid #ddd', marginTop: 16, paddingTop: 16 }}>
          <BookingForm slot={selected} onDone={() => setSelected(null)} />
        </div>
      )}
    </div>
  );
}