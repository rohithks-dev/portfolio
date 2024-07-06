package com.portfolio.repository;

import com.portfolio.entity.UserTabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CreatePortfolioRepository extends JpaRepository<UserTabEntity, Integer> {

    @Query("SELECT u from UserTabEntity u where u.user_id = :userId and u.user_name = :userName and u.secret = :secret")
    UserTabEntity findByUserName(String userName, Integer userId, String secret);

}
