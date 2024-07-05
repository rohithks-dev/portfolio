package com.portfolio.repository;

import com.portfolio.entity.UserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatePortfolioRepository extends JpaRepository<UserTabEntity, Integer> {

}
