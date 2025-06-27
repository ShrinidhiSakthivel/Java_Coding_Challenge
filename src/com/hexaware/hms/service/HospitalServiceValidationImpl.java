package com.hexaware.hms.service;

import com.hexaware.hms.dao.*;
import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HospitalServiceValidationImpl implements IHospitalServiceValidation {

    private IHospitalServiceDAO dao;

    public HospitalServiceValidationImpl() throws SQLException {
        dao = new HospitalServiceDAOImpl();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        if (appointmentId <= 0) throw new IllegalArgumentException("Appointment ID must be greater than 0.");
        return dao.getAppointmentById(appointmentId);
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId)
            throws SQLException, PatientNumberNotFoundException {
        if (patientId <= 0) throw new IllegalArgumentException("Patient ID must be greater than 0.");
        return dao.getAppointmentsForPatient(patientId);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException {
        if (doctorId <= 0) throw new IllegalArgumentException("Doctor ID must be greater than 0.");
        return dao.getAppointmentsForDoctor(doctorId);
    }

    @Override
    public boolean scheduleAppointment(Appointment appt) throws SQLException {
        validateAppointment(appt, false); // false = new appointment
        return dao.scheduleAppointment(appt);
    }

    @Override
    public boolean updateAppointment(Appointment appt) throws SQLException {
        validateAppointment(appt, true); // true = updating
        return dao.updateAppointment(appt);
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws SQLException {
        if (appointmentId <= 0) throw new IllegalArgumentException("Appointment ID must be valid.");
        return dao.cancelAppointment(appointmentId);
    }

    private void validateAppointment(Appointment appt, boolean isUpdate) {
        if (appt == null) throw new IllegalArgumentException("Appointment cannot be null.");
        if (isUpdate && appt.getAppointmentId() <= 0)
            throw new IllegalArgumentException("Appointment ID is required for update.");
        if (appt.getPatientId() <= 0)
            throw new IllegalArgumentException("Patient ID must be valid.");
        if (appt.getDoctorId() <= 0)
            throw new IllegalArgumentException("Doctor ID must be valid.");
        if (appt.getAppointmentDate() == null)
            throw new IllegalArgumentException("Appointment date cannot be null.");
        if (appt.getAppointmentDate().isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        if (appt.getDescription() == null || appt.getDescription().isEmpty())
            throw new IllegalArgumentException("Description is required.");
    }
}
