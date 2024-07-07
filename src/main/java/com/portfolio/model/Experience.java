package com.portfolio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Experience {

    private String title;
    private String company;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String about;
    private String skills;

}
