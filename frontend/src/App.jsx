import React from "react";
import Sidebar from "./components/Sidebar";
import DoctorList from "./pages/DoctorList";
import "./styles/main.css";

export default function App() {
  return (
    <div className="app">
      <Sidebar />
      <DoctorList />
    </div>
  );
}