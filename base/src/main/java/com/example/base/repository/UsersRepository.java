package com.example.base.repository;

import com.example.base.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
  boolean existsUsersByEmail(String email);


}
