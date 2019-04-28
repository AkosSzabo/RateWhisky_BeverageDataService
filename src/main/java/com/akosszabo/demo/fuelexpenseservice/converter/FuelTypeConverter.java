package com.akosszabo.demo.fuelexpenseservice.converter;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import org.springframework.core.convert.converter.Converter;

public class FuelTypeConverter implements Converter<String, FuelType> {

    @Override
    public FuelType convert(String source) {
            return FuelType.valueOf(source);
    }
}