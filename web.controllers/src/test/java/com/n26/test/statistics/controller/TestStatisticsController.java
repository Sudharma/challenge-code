package com.n26.test.statistics.controller;

import com.n26.test.rest.model.Statistics;
import com.n26.test.statistics.service.api.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
public class TestStatisticsController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticService service;


    @Test
    public void testGetStatistics() {
        Statistics statistics = new Statistics(10.0, 11.0, 12.0, 13.0, 10L);
        given(service.getStatistics()).willReturn(statistics);

        try {
            mockMvc.perform(get("/statistics")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.sum", is(statistics.getSum())))
                    .andExpect(jsonPath("$.avg", is(statistics.getAvg())))
                    .andExpect(jsonPath("$.max", is(statistics.getMax())))
                    .andExpect(jsonPath("$.min", is(statistics.getMin())));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}