package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Service
public class FuelExpenseService {
    public FuelExpense calculateFuelExpense(FuelExpenseRequest request){
        return null;
    }


}
