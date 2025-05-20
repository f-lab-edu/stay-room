package com.example.base.repository;

import com.example.base.entity.StayInfo;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StayInfoRepository extends JpaRepository<StayInfo, Long> {

}
