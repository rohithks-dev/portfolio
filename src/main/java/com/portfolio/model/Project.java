package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Project {

    private String title;
    private String description;
    private String liveURL;
    private String githubURL;
    private String skills;
    private String operation;

}
