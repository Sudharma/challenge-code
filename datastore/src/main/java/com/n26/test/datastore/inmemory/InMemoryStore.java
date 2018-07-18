package com.n26.test.datastore.inmemory;

import com.n26.test.datastore.impl.DataStoreServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStore extends ConcurrentHashMap<Date, Double> {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryStore.class);


    private InMemoryStore() {
    }

    private static InMemoryStore store = null;

    public static final InMemoryStore getInstance() {
        if (store == null)
            store = new InMemoryStore();
        return store;
    }

    public void add(Date timestamp, double amount) {
        logger.info("adding to datastore.. Date [ {} ], Amount [ {} ] ", timestamp, amount);
        store.put(timestamp, amount);
    }

    public double getAmount(Date timestamp) {
        logger.info("Retrieving from datastore for timestamp [ {} ]", timestamp);
        return store.get(timestamp);
    }


}
