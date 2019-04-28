package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.ExpenseCalculationResult;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;

import java.time.LocalDate;

public interface FuelExpenseCalculator {
    ExpenseCalculationResult calculate(FuelExpenseRequest fuelExpenseRequest, LocalDate targetDate);
}
