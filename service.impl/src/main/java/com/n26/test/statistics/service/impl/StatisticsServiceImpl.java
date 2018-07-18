package com.n26.test.statistics.service.impl;

import com.n26.test.datastore.api.DataStoreService;
import com.n26.test.rest.model.Statistics;
import com.n26.test.statistics.service.api.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StatisticsServiceImpl implements StatisticService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Autowired
    DataStoreService storeService;

    @Override
    public Statistics getStatistics() {
        List<Double> amounts = storeService.getTransactionAmount().get();
        if (amounts.isEmpty()) {
            logger.warn("No Transactions with timestamp  past 60 seconds!!");
            return new Statistics();
        }
        Double sum = StatisticsHelper.sum(amounts);
        Double avg = StatisticsHelper.avg(amounts);
        Double max = StatisticsHelper.max(amounts);
        Double min = StatisticsHelper.min(amounts);
        long count = StatisticsHelper.count(amounts);
        return new Statistics(sum, avg, max, min, count);
    }
}

final class StatisticsHelper {

    static Double sum(List<Double> amounts) {
        return amounts.stream().mapToDouble(Double::doubleValue).sum();
    }

    static Double max(List<Double> amounts) {
        return amounts == null ? 0 : amounts.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }

    static Double min(List<Double> amounts) {
        return amounts == null ? 0 : amounts.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }

    static Double avg(List<Double> amounts) {
        return amounts == null ? 0 : amounts.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    static long count(List<Double> amounts) {
        return amounts == null ? 0 : amounts.stream().count();
    }

}
