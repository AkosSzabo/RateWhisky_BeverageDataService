package com.akosszabo.demo.fuelexpenseservice.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonAutoDetect
public class FuelExpense {
    private BigDecimal costInPence;
    private BigDecimal dutyInPence;
    private BigDecimal differenceFromCurrentCostsInPence;

    public FuelExpense(final BigDecimal costInPence,final  BigDecimal dutyInPence,final  BigDecimal differenceFromCurrentCostsInPence) {
        this.costInPence = costInPence.setScale(0, BigDecimal.ROUND_HALF_UP);
        this.dutyInPence = dutyInPence.setScale(0, BigDecimal.ROUND_HALF_UP);
        this.differenceFromCurrentCostsInPence = differenceFromCurrentCostsInPence.setScale(0, BigDecimal.ROUND_HALF_UP);
    }
}
