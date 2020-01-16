package com.rf.springsecurity.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "treatment_id", nullable = false, unique = true)
    private Long treatmentId;

    @Column
    private String medicalProcedure;

    @Column
    private String medication;

    @Column
    private String surgery;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
