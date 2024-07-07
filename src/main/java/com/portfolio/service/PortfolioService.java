package com.portfolio.service;

import com.portfolio.model.DeletePortfolio;
import com.portfolio.model.PortfolioBody;
import com.portfolio.model.PortfolioResponse;

public interface PortfolioService {

    PortfolioResponse createPortfolio(PortfolioBody portfolio);

    PortfolioBody getPortfolio(String userName);

    PortfolioResponse deletePortfolio(String userName, DeletePortfolio deletePortfolio);

}
