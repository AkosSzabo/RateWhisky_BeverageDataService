package com.as.ratewhisky.beverage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BeverageDataServiceApplication.class)
@AutoConfigureMockMvc
public class BeverageDataServiceIntTest {

    @Test
    public void testULSPToDate1() {
//        final LocalDate targetDate =LocalDate.parse("2010-02-03", formatter);
////
////        final FuelExpenseRequest request = new FuelExpenseRequest(targetDate,FuelType.ULSP,new BigDecimal("30"),new BigDecimal("10"));
////        final FuelExpense result = historicalFuelExpenseService.calculateFuelExpense(request);
////
////        assertEquals(result.getCostInPence(),new BigDecimal("168"));
////        assertEquals(result.getDutyInPence(),new BigDecimal("99"));
////        assertEquals(result.getDifferenceFromCurrentCostsInPence(),new BigDecimal("-20"));
    }


}
