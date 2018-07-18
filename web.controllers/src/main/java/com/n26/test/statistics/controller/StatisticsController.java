package com.n26.test.statistics.controller;

import com.n26.test.rest.model.Statistics;
import com.n26.test.statistics.service.api.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    StatisticService service;

    /***
     *  Controller mapping to get Statistics.
     * @return @{@link Statistics}
     */
    @RequestMapping(value = "/statistics", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Statistics> getStatistics() {
        logger.info("Retrieving Statistics...");
        Statistics statistics = service.getStatistics();
        logger.info("[ {} ]", statistics);
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }
}
