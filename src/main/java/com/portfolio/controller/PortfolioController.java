package com.portfolio.controller;

import com.portfolio.model.*;
import com.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/create-portfolio")
    public ResponseEntity<PortfolioResponse> createPortfolio(
            @RequestBody PortfolioBody portfolio) {
        portfolioService.createPortfolio(portfolio);

        PortfolioResponse portfolioResponse = new PortfolioResponse();
        portfolioResponse.setMessage("Successfully Created");
        portfolioResponse.setUserName(portfolio.getUserName());
        portfolioResponse.setUserID(1);
        portfolioResponse.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<>(portfolioResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-portfolio")
    public ResponseEntity<PortfolioResponse> deletePortfolio(@RequestParam(name = "userName") String userName) {
        portfolioService.deletePortfolio(userName);
        return null;
    }

}
