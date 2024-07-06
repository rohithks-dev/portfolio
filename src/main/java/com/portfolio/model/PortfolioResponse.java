package com.portfolio.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PortfolioResponse {

    private String message;
    private Integer userID;
    private String userName;
    private HttpStatus statusCode;

}
