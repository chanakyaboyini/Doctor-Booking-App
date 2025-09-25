import React, { useEffect, useState } from 'react';
import { api } from './api';
import { Link } from 'react-router-dom';

export default function DoctorList() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    api.get('/doctors').then(res => setDoctors(res.data));
  }, []);

  return (
    <div>
      <h3>Available Doctors</h3>
      <ul>
        {doctors.map(d => (
          <li key={d.id} style={{ marginBottom: 8 }}>
            <strong>{d.name}</strong> — {d.specialty} ({d.clinic}){' '}
            <Link to={`/doctor/${d.id}`}>View slots</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}