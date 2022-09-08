package com.sellers.manager.infrastructure.repository;

import com.sellers.manager.application.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    @Query("FROM Region p WHERE p.name = :name")
    Region getByName(String name);

}
