package com.n26.test.statistics.service.impl;

import com.n26.test.datastore.api.DataStoreService;
import com.n26.test.rest.model.Statistics;
import com.n26.test.statistics.service.api.StatisticService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class StatisticsServiceImplTest {

    @TestConfiguration
    static class StatisticsServiceImplTestContextConfiguration {

        @Bean
        public StatisticService statisticsService() {
            return new StatisticsServiceImpl();
        }
    }

    @Autowired
    private StatisticService statisticsService;

    @MockBean
    private DataStoreService dataStoreService;


    @Before
    public void setup() {


    }

    @Test
    public void testGetStatistics_withValues() {
        List<Double> amounts = Stream.of(10.5, 20.5, 30.5, 40.5, 50.00, 60.00, 70.00, 80.00).collect(Collectors.toList());

        Optional<List<Double>> optional = Optional.of(amounts);
        Mockito.when(dataStoreService.getTransactionAmount()).thenReturn(optional);

        Statistics statistics = statisticsService.getStatistics();
        Assert.assertEquals(statistics.getSum(), amounts.stream().mapToDouble(Double::doubleValue).sum(), 0);
        Assert.assertEquals(statistics.getAvg(), amounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble(), 0);
        Assert.assertEquals(statistics.getMax(), amounts.stream().mapToDouble(Double::doubleValue).max().getAsDouble(), 0);
        Assert.assertEquals(statistics.getMin(), amounts.stream().mapToDouble(Double::doubleValue).min().getAsDouble(), 0);
        Assert.assertEquals(statistics.getCount(), amounts.stream().count());
    }

    @Test
    public void testGetStatistics_empty() {
        Optional<List<Double>> optional = Optional.of(Lists.emptyList());
        Mockito.when(dataStoreService.getTransactionAmount()).thenReturn(optional);

        Statistics statistics = statisticsService.getStatistics();
        Assert.assertEquals(statistics.getSum(), 0, 0);
        Assert.assertEquals(statistics.getAvg(), 0, 0);
        Assert.assertEquals(statistics.getMax(), 0, 0);
        Assert.assertEquals(statistics.getMin(), 0, 0);
        Assert.assertEquals(statistics.getCount(), 0);
    }
}
