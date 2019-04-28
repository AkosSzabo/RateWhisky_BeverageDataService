package com.akosszabo.demo.fuelexpenseservice.service.persistence;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelPriceDto;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.entity.FuelPriceRow;
import com.akosszabo.demo.fuelexpenseservice.service.DateUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Log
@Service
public class FuelPriceDaoImp implements FuelPriceDao {

    @Autowired
    private FuelPriceRepository fuelPriceRepository;

    @Override
    public FuelPriceDto findPriceForTargetDateAndType(final LocalDate targetDate, final FuelType type) {
        final Optional<FuelPriceRow> priceForTargetDate = Optional.ofNullable(fuelPriceRepository.findPriceForTargetDate(DateUtil.convertFromLocalDate(targetDate)));
        log.info("price for date: " + targetDate + " " + priceForTargetDate);
        FuelPriceDto result = priceForTargetDate.map( n -> convertEntityToDto(n, type) ).orElseThrow(() -> new RuntimeException("Price data not found for date " + targetDate));
        log.info("FuelPriceDto: " + result);
        return result;
    }

    private FuelPriceDto convertEntityToDto(FuelPriceRow rowData, final FuelType type) {
        if (FuelType.ULSP.equals(type)) {
            return convertULSPtoDto(rowData);
        } else {
            return convertULSDtoDto(rowData);
        }
    }

    private FuelPriceDto convertULSPtoDto(FuelPriceRow rowData) {
        final FuelPriceDto result = new FuelPriceDto();
        result.setType(FuelType.ULSP);
        result.setDate(rowData.getFueldate());
        result.setPumpingPrice(nullSafeConversion(rowData.getUlsppp()));
        result.setPumpingPriceWeekDifference(nullSafeConversion(rowData.getUlspppweekdiff()));
        result.setPumpingPriceYearDifference(nullSafeConversion(rowData.getUlspppyeardiff()));
        result.setDuty(nullSafeConversion(rowData.getUlspduty()));
        result.setVatPercentage(nullSafeConversion(rowData.getUlspvat()));
        return result;
    }

    private FuelPriceDto convertULSDtoDto(FuelPriceRow rowData) {
        final FuelPriceDto result = new FuelPriceDto();
        result.setType(FuelType.ULSD);
        result.setDate(rowData.getFueldate());
        result.setPumpingPrice(nullSafeConversion(rowData.getUlsdpp()));
        result.setPumpingPriceWeekDifference(nullSafeConversion(rowData.getUlsdppweekdiff()));
        result.setPumpingPriceYearDifference(nullSafeConversion(rowData.getUlsdppyeardiff()));
        result.setDuty(nullSafeConversion(rowData.getUlsdduty()));
        result.setVatPercentage(nullSafeConversion(rowData.getUlsdvat()));
        return result;
    }

    private BigDecimal nullSafeConversion(String string) {
        if(StringUtils.isEmpty(string)) {
            return new BigDecimal("0");
        } else {
            return new BigDecimal(string);
        }

    }
}
