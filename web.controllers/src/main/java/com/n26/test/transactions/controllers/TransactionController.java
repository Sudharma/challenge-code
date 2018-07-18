package com.n26.test.transactions.controllers;

import com.n26.test.rest.model.Transaction;
import com.n26.test.transaction.service.impl.api.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for Transaction
 */
@RestController
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService service;

    /**
     * Post Transaction with @{@link Transaction} as request body. <p>
     * Should return 201 code if transaction is less than 60 seconds. 204 if more
     * than 60 seconds.
     *
     * @param transactionRequest
     * @return No Content and only http status code
     */
    @PostMapping("/transactions")
    public ResponseEntity<?> createTransactions(@Valid @RequestBody Transaction transactionRequest) {
        logger.info("Posting Transaction Started");
        int status = service.createTransaction(transactionRequest.getAmount(), transactionRequest.getTimestamp());
        logger.info("Posting Transaction Completed.");
        return new ResponseEntity(HttpStatus.valueOf(status));
    }


}
