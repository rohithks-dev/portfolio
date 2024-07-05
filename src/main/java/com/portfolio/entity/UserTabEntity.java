package com.portfolio.entity;

import com.portfolio.model.Experience;
import jakarta.persistence.*;
import lombok.Data;

@Data
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
    private String created_on;

    @OneToMany
    private ExperienceTabEntity experienceTabEntity;

}
