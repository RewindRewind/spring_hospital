package com.rf.springsecurity.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "patient_id", nullable = false, unique = true)
    private Long patientId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String symptoms;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Treatment> treatments;
}
