package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "certificate_tab")
public class CertificateTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer certificate_id;

    private String name;
    private String description;
    private LocalDate year;
    private String certificate_url;
    private String skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTabEntity userTab;

}
