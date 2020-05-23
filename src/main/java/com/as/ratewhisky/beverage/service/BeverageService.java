package com.as.ratewhisky.beverage.service;

import com.as.ratewhisky.beverage.persistence.entity.Beverage;

import java.util.List;
import java.util.Optional;

public interface BeverageService {

    Optional<Beverage> getBeverageById(final Integer id);
    List<Beverage> getAllBeverages();
}
