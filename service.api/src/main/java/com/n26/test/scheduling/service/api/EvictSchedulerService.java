package com.n26.test.scheduling.service.api;

/***
 * Evict old entries from Data-store
 */
public interface EvictSchedulerService {

    boolean evictOldEntries();
}
