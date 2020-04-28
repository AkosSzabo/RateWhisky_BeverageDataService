package com.as.ratewhisky.beverage.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeveragesResource.class)
public class BeveragesResourceTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testValidRequestReturnResponse()
            throws Exception {

        //?date=2018-02-14&type=ULSP&mpg=1.2&mileage=100
        mvc.perform(get("/api/whisky")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}

