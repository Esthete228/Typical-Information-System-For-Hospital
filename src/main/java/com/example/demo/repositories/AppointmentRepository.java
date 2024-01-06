package com.example.demo.repositories;

import com.example.demo.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient WHERE a.doctor.ID = :doctorId")
    List<Appointment> findByDoctorID(@Param("doctorId") int doctorId);
}
