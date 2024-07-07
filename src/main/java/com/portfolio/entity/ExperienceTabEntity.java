package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experience_tab")
public class ExperienceTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experience_id;
    private String title;
    private String company;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String about;
    private String skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTabEntity userTab;

}
