package com.example.base.repository;

import com.example.base.entity.Seller;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

}
