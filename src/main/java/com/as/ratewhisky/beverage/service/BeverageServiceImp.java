package com.as.ratewhisky.beverage.service;

import com.as.ratewhisky.beverage.persistence.BeverageRepository;
import com.as.ratewhisky.beverage.persistence.entity.Beverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeverageServiceImp implements BeverageService {


    private BeverageRepository beverageRepository;

    @Autowired
    public BeverageServiceImp(final BeverageRepository beverageRepository) {
        this.beverageRepository = beverageRepository;
    }


    public Optional<Beverage> getBeverageById(final Integer id) {
        return  beverageRepository.findById(id);
    }

    @Override
    public List<Beverage> getAllBeverages() {
        return beverageRepository.findAll( Sort.by(Sort.Direction.ASC, "id"));
    }
}
