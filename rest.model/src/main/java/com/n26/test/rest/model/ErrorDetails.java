package com.n26.test.rest.model;

import java.util.Date;

/**
 * Error Details to be handled by @ControllerAdvice
 */
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}