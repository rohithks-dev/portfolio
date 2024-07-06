package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
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

    @Column(name = "linkedin_url")
    private String linkedIn_URL;

    @Column(name = "github_url")
    private String github_URL;
    private LocalDateTime created_on;

    @OneToMany(mappedBy = "userTab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExperienceTabEntity> experiences;

    @OneToMany(mappedBy = "userTab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTabEntity> projects;

    @OneToMany(mappedBy = "userTab", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CertificateTabEntity> certifications;

}
