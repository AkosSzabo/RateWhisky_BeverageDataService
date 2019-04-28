package com.akosszabo.demo.fuelexpenseservice.service;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;

public interface FuelExpenseService {

    FuelExpense calculateFuelExpense(FuelExpenseRequest fuelExpenseRequest);
}
