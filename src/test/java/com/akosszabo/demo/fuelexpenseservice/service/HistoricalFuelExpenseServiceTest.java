package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.ExpenseCalculationResult;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class HistoricalFuelExpenseServiceTest {


    @Mock
    FuelExpenseCalculator fuelExpenseCalculator;

    @InjectMocks
    HistoricalFuelExpenseService historicalFuelExpenseService;

    @Test
    public void testServiceProcessesRequest() {

        final LocalDate targetDate = LocalDate.now().minusMonths(2);
        final BigDecimal costTargetDate = new BigDecimal("10.500000001");
        final BigDecimal dutyTargetDate = new BigDecimal("40.3");
        final BigDecimal costToday = new BigDecimal("11.5");
        final BigDecimal dutyToday = new BigDecimal("42.3");
        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSD,mock(BigDecimal.class),mock(BigDecimal.class));
        final ExpenseCalculationResult targetDateResult = new ExpenseCalculationResult(costTargetDate, dutyTargetDate);
        final ExpenseCalculationResult todayResult = new ExpenseCalculationResult(costToday, dutyToday);

        when(fuelExpenseCalculator.calculate(eq(request),eq(targetDate))).thenReturn(targetDateResult);
        when(fuelExpenseCalculator.calculate(eq(request),eq(LocalDate.now()))).thenReturn(todayResult);

        final FuelExpense fuelExpense = historicalFuelExpenseService.calculateFuelExpense(request);

        assertTrue(fuelExpense.getCostInPence().compareTo(costTargetDate.setScale(0, BigDecimal.ROUND_HALF_UP))==0);
        assertTrue(fuelExpense.getDutyInPence().compareTo(dutyTargetDate.setScale(0, BigDecimal.ROUND_HALF_UP))==0);
        assertTrue(fuelExpense.getDifferenceFromCurrentCostsInPence().compareTo(costTargetDate.subtract(costToday).setScale(0, BigDecimal.ROUND_HALF_UP))==0);





    }
}
