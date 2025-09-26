import React from "react";

export default function SlotCalendar({ slots, selectedDate, onSelectDate, onSelectSlot }) {
  const dates = [...new Set(slots.map(s => s.date))];

  return (
    <div className="calendar">
      <h3>Available Slots</h3>
      <div className="dates">
        {dates.map(date => (
          <button
            key={date}
            className={selectedDate === date ? "selected" : ""}
            onClick={() => onSelectDate(date)}
          >
            {date}
          </button>
        ))}
      </div>
      <div className="times">
        {slots
          .filter(s => s.date === selectedDate)
          .map(s => (
            <button key={s.time} onClick={() => onSelectSlot(s)}>
              {s.time}
            </button>
          ))}
      </div>
    </div>
  );
}