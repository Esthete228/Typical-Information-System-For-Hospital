    package com.example.demo.entities;

    import jakarta.persistence.*;
    import org.hibernate.annotations.CreationTimestamp;

    import java.time.LocalDateTime;
    import java.util.Date;

    @Entity
    @Table(name = "appointment")
    public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int appointment_id;

        @CreationTimestamp
        @Column(name = "date_time", updatable = false)
        private LocalDateTime dateTime;

        @ManyToOne
        @JoinColumn(name = "doctor_id") // Assuming this is the correct column name in your table
        private Doctor doctor;

        @ManyToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;


        public int getAppointment_id() {
            return appointment_id;
        }

        public Patient getPatient() {
            return patient;
        }

        public void setPatient(Patient patient) {
            this.patient = patient;
        }


        public void setAppointment_id(int appointment_id) {
            this.appointment_id = appointment_id;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }
    }
