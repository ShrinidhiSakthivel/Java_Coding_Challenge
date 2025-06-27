package com.hexaware.hms.dao;
import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.*;
import java.sql.SQLException;
import java.util.List;

public interface IHospitalServiceDAO {

    Appointment getAppointmentById(int appointmentId) throws SQLException;

    List<Appointment> getAppointmentsForPatient(int patientId)
            throws SQLException, PatientNumberNotFoundException;

    List<Appointment> getAppointmentsForDoctor(int doctorId)
            throws SQLException;

    boolean scheduleAppointment(Appointment appt) throws SQLException;

    boolean updateAppointment(Appointment appt) throws SQLException;

    boolean cancelAppointment(int appointmentId) throws SQLException;
}
