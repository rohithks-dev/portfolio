package com.portfolio.model;

import lombok.Data;

import java.util.List;

@Data
public class PortfolioBody {

    private String userName;
    private String firstName;
    private String middleName;
    private Integer phoneNumber;
    private String email;
    //private photo;
    private String linkedInURL;
    private String githubURL;
    //private resume
    private List<Experience> experienceList;
    private List<Projects> projectList;
    private List<Certification> certificationList;

}
