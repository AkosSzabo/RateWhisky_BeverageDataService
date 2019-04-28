package com.akosszabo.demo.fuelexpenseservice.api;

import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpense;
import com.akosszabo.demo.fuelexpenseservice.domain.FuelExpenseRequest;
import com.akosszabo.demo.fuelexpenseservice.service.HistoricalFuelExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FuelExpenseCalculatorResource.class)
public class FuelExpenseCalculatorResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HistoricalFuelExpenseService historicalFuelExpenseService;

    @Test
    public void testValidRequestReturnResponse()
            throws Exception {

        FuelExpense mockFuelExpense = new FuelExpense(new BigDecimal("200.1"), new BigDecimal("20"), new BigDecimal("-20.1"));

        given(historicalFuelExpenseService.calculateFuelExpense(any(FuelExpenseRequest.class))).willReturn(mockFuelExpense);


        //?date=2018-02-14&type=ULSP&mpg=1.2&mileage=100
        mvc.perform(get("/api/expense")
                .param("date", "2018-02-14")
                .param("type", "ULSP")
                .param("mpg", "1.2")
                .param("mileage", "100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("costInPence").value("200"))
                .andExpect(jsonPath("dutyInPence").value("20"))
                .andExpect(jsonPath("differenceFromCurrentCostsInPence").value("-20"));
    }

    @Test
    public void testInvalidTypeReturnValidationError()
            throws Exception {

        //?date=2018-02-14&type=XXXULSP&mpg=1.2&mileage=100
        mvc.perform(get("/api/expense")
                .param("date", "2018-02-14")
                .param("type", "XXULSP")
                .param("mpg", "1.2")
                .param("mileage", "100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testInvalidDateReturnValidationError()
            throws Exception {

        //?date=2018-14-14&type=ULSP&mpg=1.2&mileage=100
        mvc.perform(get("/api/expense")
                .param("date", "2018-14-14")
                .param("type", "ULSP")
                .param("mpg", "1.2")
                .param("mileage", "100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test(expected = Exception.class)
    public void testFutureDateReturnValidationError()
            throws Exception {

        //?date=2022-12-14&type=ULSP&mpg=1.2&mileage=100
        mvc.perform(get("/api/expense")
                .param("date", "2022-02-14")
                .param("type", "ULSP")
                .param("mpg", "1.2")
                .param("mileage", "100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test(expected = Exception.class)
    public void testNegativeMpgReturnValidationError()
            throws Exception {

        //?date=2018-12-14&type=ULSP&mpg=-1.2&mileage=100
        mvc.perform(get("/api/expense")
                .param("date", "2018-02-14")
                .param("type", "ULSP")
                .param("mpg", "-1.2")
                .param("mileage", "100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testZeroMileageReturnValidationError()
            throws Exception {

        //?date=2018-12-14&type=ULSP&mpg=1.2&mileage=0
        mvc.perform(get("/api/expense")
                .param("date", "2018-02-14")
                .param("type", "ULSP")
                .param("mpg", "1.2")
                .param("mileage", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testMissingParameterReturnValidationError()
            throws Exception {

        //?date=2018-12-14&type=ULSP&mpg=1.2&mileage=0
        mvc.perform(get("/api/expense")
                .param("date", "2018-02-14")
                .param("type", "ULSP")
                .param("mpg", "1.2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


}

