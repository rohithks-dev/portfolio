package com.portfolio.model;

import lombok.Data;

@Data
public class PortfolioResponse {

    private String message;
    private Integer userID;
    private String userName;
    private Integer statusCode;

}
