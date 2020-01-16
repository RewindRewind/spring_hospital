package com.rf.springsecurity.dto;

import com.rf.springsecurity.entity.Patient;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PatientsDTO {
    private List<Patient> patients;
}
