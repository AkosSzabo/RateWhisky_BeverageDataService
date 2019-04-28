package com.akosszabo.demo.fuelexpenseservice.service.persistence;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelPriceDto;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.entity.FuelPriceRow;
import com.akosszabo.demo.fuelexpenseservice.service.DateUtil;
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
public class FuelPriceDaoImpTest {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate targetDate =LocalDate.parse("2012-02-03", formatter);

    @Mock
    private FuelPriceRepository fuelPriceRepository;

    @InjectMocks
    private FuelPriceDaoImp fuelPriceDaoImp;

    @Test()
    public void testFindULSP() {
        final Date dateValue = DateUtil.convertFromLocalDate(targetDate);
        String ulsppp = "1.1";
        String ulspppweekdiff = "2.2";
        String ulspppyeardiff = "3.3";
        String ulspduty = "4.4";
        String ulspvat = "5.5";

        final FuelPriceRow fuelPriceRow = new FuelPriceRow();
        fuelPriceRow.setUlsppp(ulsppp);
        fuelPriceRow.setUlspppweekdiff(ulspppweekdiff);
        fuelPriceRow.setUlspppyeardiff(ulspppyeardiff);
        fuelPriceRow.setUlspduty(ulspduty);
        fuelPriceRow.setUlspvat(ulspvat);
        fuelPriceRow.setFueldate(dateValue);

        when(fuelPriceRepository.findPriceForTargetDate(eq(dateValue))).thenReturn(fuelPriceRow);

        final FuelPriceDto result = fuelPriceDaoImp.findPriceForTargetDateAndType(targetDate, FuelType.ULSP);

        assertEquals(result.getType(),FuelType.ULSP);
        assertEquals(result.getDate(),dateValue);
        assertEquals(result.getDuty(),new BigDecimal(ulspduty));
        assertEquals(result.getPumpingPrice(),new BigDecimal(ulsppp));
        assertEquals(result.getVatPercentage(),new BigDecimal(ulspvat));
        assertEquals(result.getPumpingPriceWeekDifference(),new BigDecimal(ulspppweekdiff));
        assertEquals(result.getPumpingPriceYearDifference(),new BigDecimal(ulspppyeardiff));
    }

    @Test()
    public void testFindULSD() {
        final Date dateValue = DateUtil.convertFromLocalDate(targetDate);
        String ulsdpp = "1.1";
        String ulsdppweekdiff = "2.2";
        String ulsdppyeardiff = "3.3";
        String ulsdduty = "4.4";
        String ulsdvat = "5.5";

        final FuelPriceRow fuelPriceRow = new FuelPriceRow();
        fuelPriceRow.setUlsdpp(ulsdpp);
        fuelPriceRow.setUlsdppweekdiff(ulsdppweekdiff);
        fuelPriceRow.setUlsdppyeardiff(ulsdppyeardiff);
        fuelPriceRow.setUlsdduty(ulsdduty);
        fuelPriceRow.setUlsdvat(ulsdvat);
        fuelPriceRow.setFueldate(dateValue);

        when(fuelPriceRepository.findPriceForTargetDate(eq(dateValue))).thenReturn(fuelPriceRow);

        final FuelPriceDto result = fuelPriceDaoImp.findPriceForTargetDateAndType(targetDate, FuelType.ULSD);

        assertEquals(result.getType(),FuelType.ULSD);
        assertEquals(result.getDate(),dateValue);
        assertEquals(result.getDuty(),new BigDecimal(ulsdduty));
        assertEquals(result.getPumpingPrice(),new BigDecimal(ulsdpp));
        assertEquals(result.getVatPercentage(),new BigDecimal(ulsdvat));
        assertEquals(result.getPumpingPriceWeekDifference(),new BigDecimal(ulsdppweekdiff));
        assertEquals(result.getPumpingPriceYearDifference(),new BigDecimal(ulsdppyeardiff));
    }

    @Test()
    public void testNullValues() {
        final Date dateValue = DateUtil.convertFromLocalDate(targetDate);
        String ulsdpp = "1.1";
        String ulsdppweekdiff = "";
        String ulsdppyeardiff = null;
        String ulsdduty = "4.4";
        String ulsdvat = "5.5";

        final FuelPriceRow fuelPriceRow = new FuelPriceRow();
        fuelPriceRow.setUlsdpp(ulsdpp);
        fuelPriceRow.setUlsdppweekdiff(ulsdppweekdiff);
        fuelPriceRow.setUlsdppyeardiff(ulsdppyeardiff);
        fuelPriceRow.setUlsdduty(ulsdduty);
        fuelPriceRow.setUlsdvat(ulsdvat);
        fuelPriceRow.setFueldate(dateValue);

        when(fuelPriceRepository.findPriceForTargetDate(eq(dateValue))).thenReturn(fuelPriceRow);

        final FuelPriceDto result = fuelPriceDaoImp.findPriceForTargetDateAndType(targetDate, FuelType.ULSD);

        assertEquals(result.getType(),FuelType.ULSD);
        assertEquals(result.getDate(),dateValue);
        assertEquals(result.getDuty(),new BigDecimal(ulsdduty));
        assertEquals(result.getPumpingPrice(),new BigDecimal(ulsdpp));
        assertEquals(result.getVatPercentage(),new BigDecimal(ulsdvat));
        assertEquals(result.getPumpingPriceWeekDifference(),new BigDecimal("0"));
        assertEquals(result.getPumpingPriceYearDifference(),new BigDecimal("0"));
    }


    @Test(expected = RuntimeException.class)
    public void testPriceDataNotFound() {
        fuelPriceDaoImp.findPriceForTargetDateAndType(targetDate, FuelType.ULSP);
    }
}
