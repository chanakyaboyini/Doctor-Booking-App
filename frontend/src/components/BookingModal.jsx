import React from "react";

export default function BookingModal({ doctor, slot, onConfirm, onClose }) {
  return (
    <div className="modal">
      <h3>Confirm Booking</h3>
      <p>Doctor: {doctor.name}</p>
      <p>Time: {slot.time}</p>
      <div className="modal-actions">
        <button onClick={onConfirm}>Confirm</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
}