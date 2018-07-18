package com.n26.test.statistics.service.api;

import com.n26.test.rest.model.Statistics;


public interface StatisticService {

    /***
     * Return the statistics for last 60 seconds
     */
    Statistics getStatistics();
}
