package com.portfolio.repository;

import com.portfolio.entity.UserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreatePortfolioRepository extends JpaRepository<UserTabEntity, Integer> {

    @Query("SELECT * from user_tab u where u.user_name = :userName")
    UserTabEntity findByUserName(String userName);

}
