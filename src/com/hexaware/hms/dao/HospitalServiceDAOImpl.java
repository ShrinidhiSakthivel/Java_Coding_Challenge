package com.hexaware.hms.dao;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.*;
import com.hexaware.hms.util.DBConnUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalServiceDAOImpl implements IHospitalServiceDAO {

    private Connection conn;

    public HospitalServiceDAOImpl() throws SQLException {
        this.conn = DBConnUtil.getConnection("src/db.properties");
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) throws SQLException {
        String sql = "select * from appointments where appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAppointmentFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId)
            throws SQLException, PatientNumberNotFoundException {

        String checkSql = "select count(*) from patients where patient_id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, patientId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new PatientNumberNotFoundException("Patient ID not found: " + patientId);
            }
        }

        String sql = "select * from appointments where patient_id = ?";
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(extractAppointmentFromResultSet(rs));
            }
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) throws SQLException {
        String sql = "select * from appointments where doctor_id = ?";
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                appointments.add(extractAppointmentFromResultSet(rs));
            }
        }
        return appointments;
    }

    @Override
    public boolean scheduleAppointment(Appointment appt) throws SQLException {
        String sql = "insert into appointments (patient_id, doctor_id, appointment_date, description) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appt.getPatientId());
            stmt.setInt(2, appt.getDoctorId());
            stmt.setDate(3, Date.valueOf(appt.getAppointmentDate()));
            stmt.setString(4, appt.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateAppointment(Appointment appt) throws SQLException {
        String sql = "update appointments set appointment_date = ?, description = ? where appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(appt.getAppointmentDate()));
            stmt.setString(2, appt.getDescription());
            stmt.setInt(3, appt.getAppointmentId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean cancelAppointment(int appointmentId) throws SQLException {
        String sql = "delete from appointments where appointment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            return stmt.executeUpdate() > 0;
        }
    }

    private Appointment extractAppointmentFromResultSet(ResultSet rs) throws SQLException {
        return new Appointment(
            rs.getInt("appointment_id"),
            rs.getInt("patient_id"),
            rs.getInt("doctor_id"),
            rs.getDate("appointment_date").toLocalDate(),
            rs.getString("description")
        );
    }
}
