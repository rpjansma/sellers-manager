package com.sellers.manager.infrastructure.repository;

import com.sellers.manager.application.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
}
