package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.ExpenseCalculationResult;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelPriceDto;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.service.persistence.FuelPriceDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FuelExpenseCalculatorImpTest {

        static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private final LocalDate targetDate =LocalDate.parse("2012-02-03", formatter);

        @Mock
        private FuelPriceDao fuelPriceDao;

        @InjectMocks
        private FuelExpenseCalculatorImp fuelExpenseCalculatorImp;

        @Test
        public void testServiceProcessesRequest1() {
            final FuelType fuelType = FuelType.ULSD;
            final BigDecimal mpg = new BigDecimal("100");
            final BigDecimal mileage = new BigDecimal("10");
            final BigDecimal pumpingPrice = new BigDecimal("141");
            final BigDecimal pumpingPriceWeekDifference = new BigDecimal("0");
            final BigDecimal pumpingPriceYearDifference = new BigDecimal("0");
            final BigDecimal duty = new BigDecimal("41");
            final BigDecimal vatPercentage = new BigDecimal("21.555");
            final BigDecimal expectedCost = new BigDecimal("64.10");
            final BigDecimal expectedDuty = new BigDecimal("22.74");

            calculateExpenses(targetDate, fuelType, mpg, mileage, pumpingPrice, pumpingPriceWeekDifference, pumpingPriceYearDifference, duty, vatPercentage, expectedCost, expectedDuty);
        }

    @Test
    public void testServiceProcessesRequest2() {
        final FuelType fuelType = FuelType.ULSD;
        final BigDecimal mpg = new BigDecimal("5");
        final BigDecimal mileage = new BigDecimal("11");
        final BigDecimal pumpingPrice = new BigDecimal("80");
        final BigDecimal pumpingPriceWeekDifference = new BigDecimal("5");
        final BigDecimal pumpingPriceYearDifference = new BigDecimal("6");
        final BigDecimal duty = new BigDecimal("21");
        final BigDecimal vatPercentage = new BigDecimal("11.555");
        final BigDecimal expectedCost = new BigDecimal("800.11");
        final BigDecimal expectedDuty = new BigDecimal("235.23");

        calculateExpenses(targetDate, fuelType, mpg, mileage, pumpingPrice, pumpingPriceWeekDifference, pumpingPriceYearDifference, duty, vatPercentage, expectedCost, expectedDuty);
    }

    @Test
    public void testServiceProcessesRequest3() {
        final FuelType fuelType = FuelType.ULSP;
        final BigDecimal mpg = new BigDecimal("10");
        final BigDecimal mileage = new BigDecimal("12");
        final BigDecimal pumpingPrice = new BigDecimal("111");
        final BigDecimal pumpingPriceWeekDifference = new BigDecimal("0");
        final BigDecimal pumpingPriceYearDifference = new BigDecimal("0");
        final BigDecimal duty = new BigDecimal("41");
        final BigDecimal vatPercentage = new BigDecimal("5.555");
        final BigDecimal expectedCost = new BigDecimal("605.54");
        final BigDecimal expectedDuty = new BigDecimal("237.09");

        calculateExpenses(targetDate, fuelType, mpg, mileage, pumpingPrice, pumpingPriceWeekDifference, pumpingPriceYearDifference, duty, vatPercentage, expectedCost, expectedDuty);
    }

    private void calculateExpenses(final LocalDate targetDate, final FuelType fuelType, final BigDecimal mpg, final BigDecimal mileage, final BigDecimal pumpingPrice, final BigDecimal pumpingPriceWeekDifference, final BigDecimal pumpingPriceYearDifference, final BigDecimal duty, final BigDecimal vatPercentage, final BigDecimal expectedCost, final BigDecimal expectedDuty) {
        final FuelExpenseRequest fuelExpenseRequest = new FuelExpenseRequest(null, fuelType, mpg, mileage);
        final FuelPriceDto fuelPriceDto = new FuelPriceDto(fuelType,new Date(), pumpingPrice,pumpingPriceWeekDifference,pumpingPriceYearDifference,duty,vatPercentage);
        when(fuelPriceDao.findPriceForTargetDateAndType(eq(targetDate),eq(fuelType))).thenReturn(fuelPriceDto);

        final ExpenseCalculationResult result = fuelExpenseCalculatorImp.calculate(fuelExpenseRequest, targetDate);

        assertEquals(expectedCost.setScale(2,BigDecimal.ROUND_HALF_UP),result.getCost().setScale(2,BigDecimal.ROUND_HALF_UP));
        assertEquals(expectedDuty.setScale(2,BigDecimal.ROUND_HALF_UP),result.getDuty().setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
