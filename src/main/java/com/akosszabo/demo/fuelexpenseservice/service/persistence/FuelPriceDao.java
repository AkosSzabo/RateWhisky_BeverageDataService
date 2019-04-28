package com.akosszabo.demo.fuelexpenseservice.service.persistence;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelPriceDto;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;

import java.time.LocalDate;

public interface FuelPriceDao {
    FuelPriceDto findPriceForTargetDateAndType(LocalDate LocalDate, FuelType type);
}
