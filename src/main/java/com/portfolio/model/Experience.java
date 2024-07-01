package com.portfolio.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Experience {

    private String title;
    private String company;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String about;
    private String skills;

}
