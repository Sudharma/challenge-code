package com.n26.test.datastore.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DataStoreService {

    Date create(long timestamp, double amount);

    Optional<List<Double>> getTransactionAmount();

}
