package com.example.demo.services;

import com.example.demo.entities.Assignment;
import com.example.demo.entities.Patient;
import com.example.demo.repositories.AssignmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AssignmentRepository assignmentRepository;

    public AssignmentService(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    public void saveAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
    }

    public List<Patient> getAssignedPatientsByDoctorId(int doctorId) {
        String query = "SELECT a.patient FROM Assignment a WHERE a.doctor.id = :doctorId";
        TypedQuery<Patient> typedQuery = entityManager.createQuery(query, Patient.class);
        typedQuery.setParameter("doctorId", doctorId);
        return typedQuery.getResultList();
    }
}
