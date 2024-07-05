package com.portfolio.service;

import com.portfolio.model.PortfolioBody;
import com.portfolio.model.PortfolioResponse;

public interface PortfolioService {

    PortfolioResponse createPortfolio(PortfolioBody portfolio);

    PortfolioResponse deletePortfolio(String userName);

}
