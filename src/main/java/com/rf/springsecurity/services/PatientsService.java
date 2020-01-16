package com.rf.springsecurity.services;

import com.rf.springsecurity.dto.PatientsDTO;
import com.rf.springsecurity.entity.Patient;
import com.rf.springsecurity.entity.State;
import com.rf.springsecurity.repository.PatientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientsService {
    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientsService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    public PatientsDTO getAllPatients() {
        //TODO checking for an empty user list

        return new PatientsDTO(patientsRepository.findAll());
    }

    public void saveNewPatient (Patient patient) throws Exception{
        patientsRepository.save(patient);
    }

    public void updatePatient(Patient patient) throws Exception{
        patientsRepository.findByPatientId(patient.getPatientId()).setState(patient.getState());
    }
}
