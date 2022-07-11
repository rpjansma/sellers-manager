package com.sellers.manager.infrastructure.repository;

import com.sellers.manager.application.entity.Region;
import com.sellers.manager.application.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    @Query("FROM Seller p WHERE p.name = :name")
    Seller getByName(String name);

    @Query("FROM Seller p WHERE p.name IN :names")
    List<Seller> getByNames(Collection<String> names);

}
