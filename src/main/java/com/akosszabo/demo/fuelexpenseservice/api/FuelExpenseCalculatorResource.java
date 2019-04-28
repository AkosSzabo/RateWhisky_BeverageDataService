package com.akosszabo.demo.fuelexpenseservice.api;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.service.FuelExpenseService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@Validated
@Log
public class FuelExpenseCalculatorResource {

    @Autowired
    private FuelExpenseService fuelExpenseService;

    @RequestMapping(value = "/api/expense", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FuelExpense calculateExpense(@RequestParam @Past @DateTimeFormat(pattern="yyyy-MM-dd") final LocalDate date,
                                        @RequestParam final FuelType type,
                                        @RequestParam @Positive @NumberFormat(pattern = "#.###,##") final BigDecimal mpg,
                                        @RequestParam @Positive @NumberFormat(pattern = "#.###,##") final BigDecimal mileage) {
    final FuelExpenseRequest fuelExpenseRequest = new FuelExpenseRequest(date, type, mpg, mileage);

    return fuelExpenseService.calculateFuelExpense(fuelExpenseRequest);
    }



}
