package com.eureka.services.app.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eureka.services.app.market.model.entity.UserEureka;

@Repository
public interface UserRepository  extends JpaRepository<UserEureka, Integer>{
  
  Optional<UserEureka> findByEmail(String email);
}
