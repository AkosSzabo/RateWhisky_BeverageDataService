package com.akosszabo.demo.fuelexpenseservice.api;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import com.akosszabo.demo.fuelexpenseservice.repository.FuelPriceRepository;
import com.akosszabo.demo.fuelexpenseservice.service.FuelExpenseRequest;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.sql.SQLException;
import java.time.LocalDate;

@RestController
@Validated
@Log
public class FuelExpenseCalculatorResource {

    @Autowired
    FuelPriceRepository fuelPriceRepository;

    @RequestMapping(value = "/expense", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String calculateExpense(@RequestParam @Past @DateTimeFormat(pattern="yyyy-MM-dd") final LocalDate date,
                                   @RequestParam final FuelType type,
                                   @RequestParam @Min(0) final long mpg,
                                   @RequestParam @Min(0) final long mileage) throws SQLException {
        FuelExpenseRequest fuelExpenseRequest = new FuelExpenseRequest(date, type, mpg, mileage);

        log.info("processing request: " + fuelExpenseRequest);

        fuelPriceRepository.findAll().stream().forEach(n -> System.out.println(n));
        return "Greetings from Spring Boot!";
    }



}
