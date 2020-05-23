package com.as.ratewhisky.beverage.converter;

import com.as.ratewhisky.beverage.api.response.BeverageResponse;
import com.as.ratewhisky.beverage.persistence.entity.Beverage;
import org.springframework.core.convert.converter.Converter;


public class BeverageToBeverageResponseConverter implements Converter<Beverage, BeverageResponse> {

    @Override
    public BeverageResponse convert(Beverage beverage) {
        BeverageResponse beverageResponse = new BeverageResponse();
        beverageResponse.setName(beverage.getName());
        return beverageResponse;
    }
}
