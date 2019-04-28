package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.ExpenseCalculationResult;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Log
public class HistoricalFuelExpenseService implements FuelExpenseService {

    @Autowired
    private FuelExpenseCalculator fuelExpenseCalculator;

    public FuelExpense calculateFuelExpense(FuelExpenseRequest fuelExpenseRequest) {
        final ExpenseCalculationResult resultForTargetDate = fuelExpenseCalculator.calculate(fuelExpenseRequest, fuelExpenseRequest.getDate());
        final ExpenseCalculationResult resultForToday = fuelExpenseCalculator.calculate(fuelExpenseRequest, LocalDate.now());
        log.info("Calculated cost for target date: " + resultForTargetDate);
        log.info("Calculated cost for today: " + resultForToday);
        final FuelExpense fuelExpense = new FuelExpense(resultForTargetDate.getCost(), resultForTargetDate.getDuty(), calculateCostDifference(resultForTargetDate, resultForToday));
        log.info("Calculated expense: " + fuelExpense);
        return fuelExpense;
    }

    private BigDecimal calculateCostDifference(final ExpenseCalculationResult resultForTargetDate, final ExpenseCalculationResult resultForToday) {
        return resultForTargetDate.getCost().subtract(resultForToday.getCost());
    }


}
