package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "project_tab")
public class ProjectTabEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer project_id;

    private String title;
    private String description;
    private String skills;
    private String live_URL;
    private String github_URL;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTabEntity userTab;

}
