package com.n26.test.datastore.impl;

import com.n26.test.datastore.api.DataStoreService;
import com.n26.test.datastore.inmemory.InMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
public class DataStoreServiceImpl implements DataStoreService {

    private static final Logger logger = LoggerFactory.getLogger(DataStoreServiceImpl.class);

    @Override
    public Date create(long timestamp, double amount) {
        Date date = Date.from(Instant.ofEpochMilli(timestamp));
        InMemoryStore.getInstance().add(date, amount);
        return date;
    }

    @Override
    public Optional<List<Double>> getTransactionAmount() {
        final Instant now = Instant.now();
        logger.info("Fetching transactions from time  [{}] less than 60 seconds...", Date.from(now));

        List<Double> amounts = InMemoryStore.getInstance().entrySet().stream().map((a) -> {
            Instant then = a.getKey().toInstant();
            Duration duration = Duration.between(then, now);
            return duration.getSeconds() < 60 ? a.getValue() : null;
        }).collect(Collectors.toList());

        return Optional.of(amounts);
    }
}


