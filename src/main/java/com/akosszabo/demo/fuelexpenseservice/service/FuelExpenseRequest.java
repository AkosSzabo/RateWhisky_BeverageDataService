package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FuelExpenseRequest {

    private LocalDate date;
    private FuelType type;
    private long mpg;
    private long mileage;

}
