package com.hexaware.hms.entity;

import java.time.LocalDate;

public class Appointment {

    private int appointmentId;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;
    private String description;

    public Appointment() {}

    public Appointment(int appointmentId, int patientId, int doctorId,
                       LocalDate appointmentDate, String description) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }


    public int getAppointmentId() {
    	return appointmentId; 
    	}
    public void setAppointmentId(int appointmentId) { 
    	this.appointmentId = appointmentId;
    	}

    public int getPatientId() {
    	return patientId; 
    	}
    public void setPatientId(int patientId) { 
    	this.patientId = patientId; 
    	}

    public int getDoctorId() { 
    	return doctorId; 
    	}
    public void setDoctorId(int doctorId) {
    	this.doctorId = doctorId; 
    	}

    public LocalDate getAppointmentDate() {
    	return appointmentDate; 
    	}
    public void setAppointmentDate(LocalDate appointmentDate) { 
    	this.appointmentDate = appointmentDate; 
    	}

    public String getDescription() {
    	return description; 
    	}
    public void setDescription(String description) { 
    	this.description = description; 
    	}

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", appointmentDate=" + appointmentDate +
                ", description='" + description + '\'' +
                '}';
    }
}
