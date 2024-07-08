package com.portfolio.service;

import com.portfolio.model.DeletePortfolio;
import com.portfolio.model.PortfolioBody;
import com.portfolio.model.PortfolioResponse;

public interface PortfolioService {

    PortfolioResponse createPortfolio(PortfolioBody portfolio);

    PortfolioBody getPortfolio(String userName);

    PortfolioResponse updatePortfolio(String userName, PortfolioBody portfolioBody);

    PortfolioResponse deletePortfolio(String userName, DeletePortfolio deletePortfolio);

    PortfolioResponse verifyPortfolio(Integer userId, String userName, String secret);
}
