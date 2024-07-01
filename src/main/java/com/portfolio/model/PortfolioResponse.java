package com.portfolio.model;

import lombok.Data;

@Data
public class PortfolioResponse {

    private String message;
    private String userID;
    private String userName;
    private Integer statusCode;

}
