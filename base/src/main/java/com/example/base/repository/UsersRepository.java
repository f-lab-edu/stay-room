package com.example.base.repository;

import com.example.base.entity.Users;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Id> {
  boolean existsUsersByEmail(String email);


}
