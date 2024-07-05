package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "user_tab")
public class UserTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String user_name;
    private String secret;
    private String first_name;
    private String middle_name;
    private String last_name;
    private Integer phone;
    private String email_id;
    private String linkedIn_URL;
    private String github_URL;
    private LocalDateTime created_on;

    @OneToMany(mappedBy = "userTab")
    private List<ExperienceTabEntity> experiences;

    @OneToMany(mappedBy = "userTab")
    private List<ProjectTabEntity> projects;

    @OneToMany(mappedBy = "userTab")
    private List<CertificateTabEntity> certifications;

}
