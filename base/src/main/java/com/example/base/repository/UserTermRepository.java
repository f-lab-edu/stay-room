package com.example.base.repository;

import com.example.base.entity.UserTerm;
import com.example.base.entity.UserTermId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermRepository extends JpaRepository<UserTerm, UserTermId> {

}
