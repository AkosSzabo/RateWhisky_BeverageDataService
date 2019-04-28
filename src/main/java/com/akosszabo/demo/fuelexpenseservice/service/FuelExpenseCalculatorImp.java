package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.ExpenseCalculationResult;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelPriceDto;
import com.akosszabo.demo.fuelexpenseservice.service.persistence.FuelPriceDao;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Log
@Service
public class FuelExpenseCalculatorImp implements FuelExpenseCalculator {

    private static BigDecimal exchangeRateToLitresFromGallons = new BigDecimal("4.54609");

    @Autowired
    private FuelPriceDao fuelPriceDao;

    public ExpenseCalculationResult calculate(FuelExpenseRequest fuelExpenseRequest, LocalDate targetDate) {

        final FuelPriceDto fuelPrice = fuelPriceDao.findPriceForTargetDateAndType(targetDate, fuelExpenseRequest.getType());
        log.info("Fuel price: " + fuelPrice);
        final BigDecimal fuelUsedInGallons = fuelExpenseRequest.getMileage().divide(fuelExpenseRequest.getMpg(),2,BigDecimal.ROUND_HALF_UP);

        final BigDecimal fuelUsedInLitres = convertGallonsToLitres(fuelUsedInGallons);
        final BigDecimal cost = calculateCost(fuelUsedInLitres, fuelPrice.getPumpingPrice());
        final BigDecimal duty = calculateDuty(fuelPrice, fuelUsedInLitres);
        final ExpenseCalculationResult expenseCalculationResult = new ExpenseCalculationResult(cost, duty);
        log.info("For request: " +  fuelExpenseRequest + " calculated  fuelUsedInLitres: " + fuelUsedInLitres);
        log.info("ExpenseCalculationResult " + expenseCalculationResult);
        return expenseCalculationResult;
    }

    private BigDecimal calculateDuty(final FuelPriceDto fuelPrice, final BigDecimal fuelUsedInLitres) {
        return fuelUsedInLitres.multiply(fuelPrice.getDuty().multiply(fuelPrice.getVatPercentage().divide(new BigDecimal(100),2,RoundingMode.HALF_EVEN).add(BigDecimal.ONE)));
    }

    private BigDecimal calculateCost(final BigDecimal fuelUsedInLitres, final BigDecimal pumpingPrice) {
        return fuelUsedInLitres.multiply(pumpingPrice);
    }

    private BigDecimal convertGallonsToLitres(BigDecimal gallons) {
        return exchangeRateToLitresFromGallons.multiply(gallons);
    }


}
