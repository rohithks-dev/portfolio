package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Certification {

    private String name;
    private LocalDate year;
    private String description;
    private String certificateURL;
    private String skills;
    private String operation;

}
