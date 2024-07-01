package com.portfolio.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Certification {

    private String name;
    private LocalDate year;
    private String description;
    private String certificateURL;
    private String Skills;

}
