package com.akosszabo.demo.fuelexpenseservice.service.persistence;

import com.akosszabo.demo.fuelexpenseservice.entity.FuelPriceRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FuelPriceRepository extends JpaRepository<FuelPriceRow, Long> {

    @Query( value = "SELECT * FROM fuel_price f where f.fuel_date <= :targetDate" +
    " and f.fuel_date = (SELECT max(f2.fuel_date) from fuel_price f2 where f2.fuel_date <= :targetDate)",
            nativeQuery = true)
    FuelPriceRow findPriceForTargetDate(@Param("targetDate") Date targetDate);
    }
