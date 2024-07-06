package com.portfolio.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PortfolioResponse {

    private String message;
    private Integer userID;
    private String userName;
    private HttpStatus statusCode;

}
