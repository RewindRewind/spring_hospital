package com.rf.springsecurity.repository;

import com.rf.springsecurity.entity.Patient;
import com.rf.springsecurity.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
        Patient findByFirstNameAndLastName(String firstName, String lastName);
        Patient findByPatientId(Long Id);

        @Query(value="SELECT p FROM Patient p LEFT JOIN FETCH p.treatments patient")
        List<Patient> findAll();

        @Modifying
        @Query("update Patient p set p.state = ?1 where p.patientId = ?2")
        void updatePatientState(State state, Long userId);
}