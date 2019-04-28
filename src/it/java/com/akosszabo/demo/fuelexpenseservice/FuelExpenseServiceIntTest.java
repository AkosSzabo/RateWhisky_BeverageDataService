package com.akosszabo.demo.fuelexpenseservice;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.service.HistoricalFuelExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = FuelExpenseServiceApplication.class)
@AutoConfigureMockMvc
public class FuelExpenseServiceIntTest {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    private HistoricalFuelExpenseService historicalFuelExpenseService;

    @Test
    public void testULSPToDate1() {
        final LocalDate targetDate =LocalDate.parse("2010-02-03", formatter);

        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSP,new BigDecimal("30"),new BigDecimal("10"));
        final FuelExpense result = historicalFuelExpenseService.calculateFuelExpense(request);

        assertEquals(result.getCostInPence(),new BigDecimal("168"));
        assertEquals(result.getDutyInPence(),new BigDecimal("99"));
        assertEquals(result.getDifferenceFromCurrentCostsInPence(),new BigDecimal("-20"));
    }

    @Test
    public void testULSDToDate1() {
        final LocalDate targetDate =LocalDate.parse("2010-02-03", formatter);

        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSD,new BigDecimal("30"),new BigDecimal("10"));
        final FuelExpense result = historicalFuelExpenseService.calculateFuelExpense(request);

        assertEquals(result.getCostInPence(),new BigDecimal("171"));
        assertEquals(result.getDutyInPence(),new BigDecimal("99"));
        assertEquals(result.getDifferenceFromCurrentCostsInPence(),new BigDecimal("-30"));
    }

    @Test
    public void testULSPToDate2() {
        final LocalDate targetDate =LocalDate.parse("2004-02-03", formatter);

        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSP,new BigDecimal("60"),new BigDecimal("100"));
        final FuelExpense result = historicalFuelExpenseService.calculateFuelExpense(request);

        assertEquals(result.getCostInPence(),new BigDecimal("581"));
        assertEquals(result.getDutyInPence(),new BigDecimal("422"));
        assertEquals(result.getDifferenceFromCurrentCostsInPence(),new BigDecimal("-372"));
    }

    @Test
    public void testULSDToDate2() {
        final LocalDate targetDate =LocalDate.parse("2004-02-03", formatter);

        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSD,new BigDecimal("60"),new BigDecimal("100"));

        final FuelExpense result = historicalFuelExpenseService.calculateFuelExpense(request);
        System.out.println(result);

        assertEquals(result.getCostInPence(),new BigDecimal("593"));
        assertEquals(result.getDutyInPence(),new BigDecimal("422"));
        assertEquals(result.getDifferenceFromCurrentCostsInPence(),new BigDecimal("-424"));
    }
}
