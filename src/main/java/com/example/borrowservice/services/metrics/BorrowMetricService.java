package com.example.borrowservice.services.metrics;

import com.example.borrowservice.models.Borrow;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BorrowMetricService {

    private Counter borrowCreatedCounter;
    private Counter borrowReceivedCounter;
    private final MeterRegistry meterRegistry;

    @Autowired
    public BorrowMetricService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initBorrowCounters();
    }

    private void initBorrowCounters() {
        log.info("init all borrow counters");
        borrowReceivedCounter = Counter.builder("borrows.received")
                .description("number of times all borrows were received")
                .register(meterRegistry);
        borrowCreatedCounter = Counter.builder("borrows.created")
                .description("number of borrows created")
                .register(meterRegistry);
    }

    public void processCreation(Borrow createdBorrow) {
        log.debug("incrementing borrow created counter, createdBorrowObject: " + createdBorrow);
        borrowCreatedCounter.increment();
    }

    public void processReceived() {
        log.debug("incrementing all borrows received counter");
        borrowReceivedCounter.increment();
    }
}
