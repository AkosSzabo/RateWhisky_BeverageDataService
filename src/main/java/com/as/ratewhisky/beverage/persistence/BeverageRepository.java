package com.as.ratewhisky.beverage.persistence;

import com.as.ratewhisky.beverage.persistence.entity.Beverage;
import com.as.ratewhisky.beverage.persistence.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeverageRepository extends JpaRepository<Beverage, Integer> {


    }
