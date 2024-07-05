package com.portfolio.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PortfolioBody {

    private String userName;
    private String firstName;
    private String middleName;
    private String lastName;
    private Integer phoneNumber;
    private String email;
    //private photo;
    private String linkedInURL;
    private String githubURL;
    private String secret;
    //private resume
    private List<Experience> experienceList;
    private List<Project> projectList;
    private List<Certification> certificationList;
    private LocalDateTime createdOn;
}
