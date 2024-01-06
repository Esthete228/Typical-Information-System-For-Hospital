package com.example.demo.services;
import com.example.demo.entities.Appointment;
import com.example.demo.entities.Patient;
import com.example.demo.repositories.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getDoctorAppointments(int doctorId) {
        return appointmentRepository.findByDoctorID(doctorId);
    }

    public void deleteAppointment(int appointment_id) {
        appointmentRepository.deleteById(appointment_id);
    }
}
