package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "experience_tab")
public class ExperienceTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experience_id;

    private String title;
    private String company;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private String about;
    private String skills;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTabEntity userTab;

}
