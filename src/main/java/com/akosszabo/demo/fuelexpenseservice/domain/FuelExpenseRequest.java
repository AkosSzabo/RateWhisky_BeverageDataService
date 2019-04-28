package com.akosszabo.demo.fuelexpenseservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FuelExpenseRequest {

    private LocalDate date;
    private FuelType type;
    private BigDecimal mpg;
    private BigDecimal mileage;

}
