package com.n26.test.scheduler.service.impl;

import com.n26.test.datastore.inmemory.InMemoryStore;
import com.n26.test.scheduling.service.api.EvictSchedulerService;
import com.n26.test.statistics.service.impl.StatisticsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class EvictSchedulerServiceImpl implements EvictSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(EvictSchedulerServiceImpl.class);


    @Override
    @Scheduled(fixedRate = 10000)
    public boolean evictOldEntries() {
        logger.debug("Cleaning datastore for optimum usage..");
        Instant now = Instant.now();
        boolean isCleaned = InMemoryStore.getInstance().entrySet().removeIf(d -> {
            Instant instant = d.getKey().toInstant();
            return Duration.between(instant, now).getSeconds() > 60;
        });
        if (isCleaned)
            logger.info("Evicted  entries with timestamp more than 60 seconds from current time [ {} ] ", Date.from(now));
        else
            logger.info("No old entries ");
        return isCleaned;
    }
}
