package com.example.borrowservice.services;

import com.example.borrowservice.models.Borrow;
import com.example.borrowservice.repos.BorrowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowService {

    private final BorrowRepository borrowRepository;

    public List<Borrow> getAllBorrows() {
        log.debug("getting all borrows");
        return borrowRepository.findAll();
    }

    public Borrow createBorrow(Borrow borrow) {
        log.debug("creating borrow " + borrow);
        Borrow createdBorrow = borrowRepository.save(borrow);
        log.debug("created borrow " + createdBorrow);

        return createdBorrow;
    }
}
