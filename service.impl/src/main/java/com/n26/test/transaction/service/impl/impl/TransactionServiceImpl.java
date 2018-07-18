package com.n26.test.transaction.service.impl.impl;

import com.n26.test.datastore.api.DataStoreService;
import com.n26.test.transaction.service.impl.api.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Implementation of @{@link TransactionService}
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Autowired
    DataStoreService service;

    /***
     * Create Transaction
     * @param amount type double
     * @param timestamp type unix epoch in milli sec
     * @return http status code in integer format.
     */
    @Override
    public int createTransaction(double amount, long timestamp) {
        Date date = service.create(timestamp, amount);
        logger.info("converting timestamp to date");
        logger.info("timestamp --> {}. date --> {}", timestamp, date);
        Duration duration = Duration.between(date.toInstant(), Instant.now());
        if (duration.getSeconds() > 60) {
            logger.warn("Transaction greater than 60 Seconds.");
            return 204;
        }
        logger.info("Transaction less than 60 Seconds.");
        return 201;
    }
}
