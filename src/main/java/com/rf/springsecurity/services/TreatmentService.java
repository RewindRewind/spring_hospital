package com.rf.springsecurity.services;

import com.rf.springsecurity.dto.TreatmentsDTO;
import com.rf.springsecurity.dto.UsersDTO;
import com.rf.springsecurity.entity.Patient;
import com.rf.springsecurity.entity.Treatment;
import com.rf.springsecurity.entity.User;
import com.rf.springsecurity.repository.TreatmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService(TreatmentRepository treatmentRepository){
        this.treatmentRepository = treatmentRepository;
    }

    public TreatmentsDTO getAllTreatments() {
        //TODO checking for an empty user list

        return new TreatmentsDTO(treatmentRepository.findAllTreatments());
    }

    public void saveNewTreatment (Treatment treatment) throws Exception{
        treatmentRepository.save(treatment);
    }

    public void updateTreatment(Patient patient, Long Id){
        treatmentRepository.updateTreatmentWithPatient(patient, Id);
    }
}
