package com.akosszabo.demo.fuelexpenseservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuelPriceDto {

    private FuelType type;
    private Date date;
    private BigDecimal pumpingPrice;
    private BigDecimal pumpingPriceWeekDifference;
    private BigDecimal pumpingPriceYearDifference;
    private BigDecimal duty;
    private BigDecimal vatPercentage;
}
