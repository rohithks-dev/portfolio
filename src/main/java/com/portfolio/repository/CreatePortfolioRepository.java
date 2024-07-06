package com.portfolio.repository;

import com.portfolio.entity.UserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreatePortfolioRepository extends JpaRepository<UserTabEntity, Integer> {

    @Query("SELECT u from UserTabEntity u where u.user_name = :userName")
    UserTabEntity findByUserName(String userName);

}
