    package com.example.demo.entities;

    import jakarta.persistence.*;

    import java.util.Date;

    @Entity
    @Table(name = "appointment")
    public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int appointment_id;

        @Column(name = "date")
        private Date date;

        @ManyToOne
        @JoinColumn(name = "doctor_id") // Assuming this is the correct column name in your table
        private Doctor doctor;

        public int getAppointment_id() {
            return appointment_id;
        }

        public void setAppointment_id(int appointment_id) {
            this.appointment_id = appointment_id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }
    }
