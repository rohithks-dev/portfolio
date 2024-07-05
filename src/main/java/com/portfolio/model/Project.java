package com.portfolio.model;

import lombok.Data;

@Data
public class Project {

    private String title;
    private String description;
    private String liveURL;
    private String githubURL;
    private String skills;

}
