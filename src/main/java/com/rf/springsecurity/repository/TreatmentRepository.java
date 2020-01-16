package com.rf.springsecurity.repository;

import com.rf.springsecurity.entity.Patient;
import com.rf.springsecurity.entity.Treatment;
import com.rf.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    Treatment findByTreatmentId(Long Id);

    @Modifying
    @Query("update Treatment t set t.patient = ?1 where t.treatmentId = ?2")
    void updateTreatmentWithPatient(Patient patient, Long userId);

    @Query(value="SELECT t FROM Treatment t LEFT JOIN FETCH t.patient treatment")
    List<Treatment> findAllTreatments();
}
