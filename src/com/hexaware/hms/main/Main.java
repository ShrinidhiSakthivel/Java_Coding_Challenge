package com.hexaware.hms.main;

import com.hexaware.hms.entity.Appointment;
import com.hexaware.hms.exception.PatientNumberNotFoundException;
import com.hexaware.hms.service.HospitalServiceValidationImpl;
import com.hexaware.hms.service.IHospitalServiceValidation;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            IHospitalServiceValidation service = new HospitalServiceValidationImpl();

            while (true) {
                System.out.println("\nHospital Management Menu");
                System.out.println("1. Schedule Appointment");
                System.out.println("2. View Appointments for Patient");
                System.out.println("3. View Appointments for Doctor");
                System.out.println("4. Get Appointment by ID");
                System.out.println("5. Update Appointment");
                System.out.println("6. Cancel Appointment");
                System.out.println("7. Exit");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Enter Patient ID: ");
                        int pid = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Doctor ID: ");
                        int did = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        LocalDate date = LocalDate.parse(sc.nextLine());
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();

                        Appointment appt = new Appointment(0, pid, did, date, desc);
                        boolean added = service.scheduleAppointment(appt);
                        System.out.println(added ? "Appointment scheduled successfully." : "Failed to schedule.");
                        break;

                    case 2:
                        System.out.print("Enter Patient ID: ");
                        int patientId = Integer.parseInt(sc.nextLine());
                        List<Appointment> patientAppointments = service.getAppointmentsForPatient(patientId);
                        if (patientAppointments.isEmpty()) {
                            System.out.println("No appointments found for the patient.");
                        } else {
                            System.out.println("Appointments for Patient:");
                            for (Appointment a : patientAppointments) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Doctor ID: ");
                        int doctorId = Integer.parseInt(sc.nextLine());
                        List<Appointment> doctorAppointments = service.getAppointmentsForDoctor(doctorId);
                        if (doctorAppointments.isEmpty()) {
                            System.out.println("No appointments found for the doctor.");
                        } else {
                            System.out.println("Appointments for Doctor:");
                            for (Appointment a : doctorAppointments) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Appointment ID: ");
                        int aid = Integer.parseInt(sc.nextLine());
                        Appointment found = service.getAppointmentById(aid);
                        if (found == null) {
                            System.out.println("Appointment not found.");
                        } else {
                            System.out.println("Appointment Details: " + found);
                        }
                        break;

                    case 5:
                    	System.out.print("Enter appointment ID to update: ");
                    	int id = Integer.parseInt(sc.nextLine());
                    	Appointment oldAppt = service.getAppointmentById(id);

                    	if (oldAppt == null) {
                    	    System.out.println("No appointment found with ID: " + id);
                    	    return;
                    	}

                    	System.out.print("Enter new appointment date (YYYY-MM-DD): ");
                    	LocalDate newDate = LocalDate.parse(sc.nextLine());

                    	System.out.print("Enter new description: ");
                    	String newDesc = sc.nextLine();

                    	Appointment updatedAppt = new Appointment(id,oldAppt.getPatientId(),oldAppt.getDoctorId(),newDate,newDesc);
                    	boolean updated = service.updateAppointment(updatedAppt);
                    	System.out.println(updated ? "Appointment updated." : "Update failed.");


                    case 6:
                        System.out.print("Enter Appointment ID to cancel: ");
                        int cancelId = Integer.parseInt(sc.nextLine());
                        boolean cancelled = service.cancelAppointment(cancelId);
                        System.out.println(cancelled ? "Appointment cancelled." : "Cancel failed.");
                        break;

                    case 7:
                        System.out.println("Exiting");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (PatientNumberNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
