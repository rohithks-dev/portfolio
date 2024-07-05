package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "experience_tab")
public class CertificateTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer certificate_id;

    private String name;
    private String description;
    private LocalDate company;
    private LocalDate year;
    private String certificate_URL;
    private String skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTabEntity userTab;

}
