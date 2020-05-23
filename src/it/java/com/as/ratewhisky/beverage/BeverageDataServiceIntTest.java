package com.as.ratewhisky.beverage;

import com.as.ratewhisky.beverage.persistence.BeverageRepository;
import com.as.ratewhisky.beverage.persistence.entity.Beverage;
import com.as.ratewhisky.beverage.service.BeverageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = BeverageDataServiceApplication.class)
@AutoConfigureMockMvc
public class BeverageDataServiceIntTest {

    @Autowired
    private BeverageService beverageService;

    @MockBean
    private BeverageRepository beverageRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllItems() throws Exception {
        final ArrayList<Beverage> list = new ArrayList<>();
        final Beverage beverage1 = new Beverage();
        final String beverageName1 = "Beverage1";
        beverage1.setName(beverageName1);
        list.add(beverage1);
        final Beverage beverage2 = new Beverage();
        final String beverageName2 = "Beverage2";
        beverage2.setName(beverageName2);
        list.add(beverage2);
        when(beverageRepository.findAll(any(Sort.class))).thenReturn(list);

        mockMvc.perform(get("/api/beverage/all")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].name").value(beverageName2))
                .andExpect(jsonPath("$[0].name").value(beverageName1));

    }

    @Test
    public void testGetItemById() throws Exception {
        final Integer id = 1;
        final Beverage beverage1 = new Beverage();
        final String beverageName1 = "Beverage1";
        beverage1.setName(beverageName1);
        final Optional<Beverage> beverage = Optional.of(beverage1);
        when(beverageRepository.findById(eq(id))).thenReturn(beverage);

        mockMvc.perform(get("/api/beverage")
                .param("id", id.toString())
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(beverageName1));
      }




}
