package com.n26.test.datastore.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * InMemory DataStore for storing the Transactions. <p>
 * extends the @{@link ConcurrentHashMap} which supports concurrent operations.
 */
public class InMemoryStore extends ConcurrentHashMap<Date, Double> {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryStore.class);

    /**
     * keep it simple private constructor
     */
    private InMemoryStore() {
    }

    /**
     * Static initializers are  executed sequentially hence below line is thread safe
     */
    private static InMemoryStore store = new InMemoryStore();

    /**
     * Get Instance of {@link InMemoryStore}
     *
     * @return
     */
    public static final InMemoryStore getInstance() {
        return store;
    }

    /**
     * Add the Timestamp and amount to store. <p>
     * The timestamp is in standard date format converted.
     *
     * @param timestamp timestamp in proper @{@link Date}
     * @param amount
     */
    public void add(Date timestamp, double amount) {
        logger.info("adding to datastore.. Date [ {} ], Amount [ {} ] ", timestamp, amount);
        store.put(timestamp, amount);
    }

    /***
     * @deprecated
     * @param timestamp
     * @return
     */
    public double getAmount(Date timestamp) {
        logger.info("Retrieving from datastore for timestamp [ {} ]", timestamp);
        return store.get(timestamp);
    }


}
