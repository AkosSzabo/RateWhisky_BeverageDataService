package com.akosszabo.demo.fuelexpenseservice.repository;

import com.akosszabo.demo.fuelexpenseservice.entity.FuelPriceRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelPriceRepository extends JpaRepository<FuelPriceRow, Long> {
    }
