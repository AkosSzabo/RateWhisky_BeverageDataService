package com.akosszabo.demo.fuelexpenseservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ExpenseCalculationResult {
    private BigDecimal cost;
    private BigDecimal duty;
}
