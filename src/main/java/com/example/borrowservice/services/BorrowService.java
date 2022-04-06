package com.example.borrowservice.services;

import com.example.borrowservice.models.Borrow;
import com.example.borrowservice.repos.BorrowRepository;
import com.example.borrowservice.services.metrics.BorrowMetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMetricService borrowMetricService;

    public List<Borrow> getAllBorrows() {
        log.debug("getting all borrows");
        borrowMetricService.processReceived();
        return borrowRepository.findAll();
    }

    public Borrow createBorrow(Borrow borrow) {
        log.debug("creating borrow " + borrow);
        Borrow createdBorrow = borrowRepository.save(borrow);
        borrowMetricService.processCreation(borrow);
        log.debug("created borrow " + createdBorrow);

        return createdBorrow;
    }
}
