package com.portfolio.controller;

import com.portfolio.model.*;
import com.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/create-portfolio")
    public PortfolioResponse createPortfolio(
            @RequestBody PortfolioBody portfolio) {
        portfolioService.createPortfolio(portfolio);
        PortfolioResponse portfolioResponse = new PortfolioResponse();
        portfolioResponse.setMessage("Successfully Created");
        portfolioResponse.setUserName(portfolio.getUserName());
        portfolioResponse.setUserID(1);
        portfolioResponse.setStatusCode(HttpStatus.CREATED);
        return portfolioResponse;
    }

    @DeleteMapping("/delete-portfolio")
    public PortfolioResponse deletePortfolio(@RequestParam(name = "userName") String userName, @RequestBody DeletePortfolio deletePortfolio) {
        return portfolioService.deletePortfolio(userName, deletePortfolio);
    }
}
