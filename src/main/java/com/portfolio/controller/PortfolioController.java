package com.portfolio.controller;

import com.portfolio.model.*;
import com.portfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/create-portfolio")
    public PortfolioResponse createPortfolio(
            @RequestBody PortfolioBody portfolio) {
        return portfolioService.createPortfolio(portfolio);
    }

    @GetMapping("/{userName}")
    public PortfolioBody getPortfolio(@PathVariable String userName) {
        return portfolioService.getPortfolio(userName);
    }

    @GetMapping("/verify-portfolio")
    public PortfolioResponse verifyPortfolio(
            @RequestParam("userName") String userName,
            @RequestParam("userId") Integer userId,
            @RequestParam("secret") String secret) {
        return portfolioService.verifyPortfolio(userId, userName, secret);
    }

    @PutMapping("/update-portfolio/{userName}")
    public PortfolioResponse updatePortfolio(@PathVariable String userName, @RequestBody PortfolioBody portfolio) {
        return portfolioService.updatePortfolio(userName, portfolio);
    }

    @DeleteMapping("/delete-portfolio")
    public PortfolioResponse deletePortfolio(@RequestParam(name = "userName") String userName, @RequestBody DeletePortfolio deletePortfolio) {
        return portfolioService.deletePortfolio(userName, deletePortfolio);
    }
}
