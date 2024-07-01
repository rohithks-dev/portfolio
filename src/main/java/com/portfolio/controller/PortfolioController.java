package com.portfolio.controller;

import com.portfolio.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {

    @PostMapping("/create-portfolio")
    public ResponseEntity<PortfolioResponse> createPortfolio(
            @RequestBody PortfolioBody portfolio) {

        PortfolioResponse portfolioResponse = new PortfolioResponse();
        portfolioResponse.setMessage("Successfully Created");
        portfolioResponse.setUserName(portfolio.getUserName());
        portfolioResponse.setUserID("ID1");
        portfolioResponse.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(portfolioResponse, HttpStatus.CREATED);
    }

}
