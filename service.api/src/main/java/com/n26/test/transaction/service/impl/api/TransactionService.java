package com.n26.test.transaction.service.impl.api;


/***
 * Service for Transactions
 */
public interface TransactionService {

    /***
     * Create transaction by supplying timestamp and amounr
     * @param amount type double
     * @param timestamp type unix epoch in milli sec
     * @return http status code . 201 CREATED or 204 NO CONTENT
     */
    int createTransaction(double amount, long timestamp);
}
