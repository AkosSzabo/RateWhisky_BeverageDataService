package com.as.ratewhisky.beverage.persistence;

import com.as.ratewhisky.beverage.persistence.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {


    }
