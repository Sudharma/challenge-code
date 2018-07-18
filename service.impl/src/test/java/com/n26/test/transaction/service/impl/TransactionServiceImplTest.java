package com.n26.test.transaction.service.impl;

import com.n26.test.datastore.api.DataStoreService;
import com.n26.test.rest.model.Statistics;
import com.n26.test.statistics.service.api.StatisticService;
import com.n26.test.statistics.service.impl.StatisticsServiceImpl;
import com.n26.test.transaction.service.impl.api.TransactionService;
import com.n26.test.transaction.service.impl.impl.TransactionServiceImpl;
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

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
public class TransactionServiceImplTest {

    @TestConfiguration
    static class TransactionServiceImplTestContextConfiguration {

        @Bean
        public TransactionService transactionService() {
            return new TransactionServiceImpl();
        }
    }

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private DataStoreService dataStoreService;


    @Test
    public void testCreateTransaction_withValidValues() {

        Instant now = Instant.now();

        Mockito.when(dataStoreService.create(now.minusSeconds(1).toEpochMilli(), 100)).thenReturn(Date.from(now.minusSeconds(1)));
        Mockito.when(dataStoreService.create(now.minusSeconds(61).toEpochMilli(), 200)).thenReturn(Date.from(now.minusSeconds(61)));


        int status = transactionService.createTransaction( 100,now.minusSeconds(1).toEpochMilli());
        Assert.assertEquals(201, status);

        status = transactionService.createTransaction( 200,now.minusSeconds(61).toEpochMilli());
        Assert.assertEquals(204, status);
    }
}
