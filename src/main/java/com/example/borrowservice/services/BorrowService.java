package com.example.borrowservice.services;

import com.example.borrowservice.models.Borrow;
import com.example.borrowservice.repos.BorrowRepository;
import com.example.borrowservice.services.metrics.BorrowMetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMetricService borrowMetricService;
    private final BookService bookService;
    private final UserService userService;

    public List<Borrow> getAllBorrows() {
        log.debug("getting all borrows");
        borrowMetricService.processReceived();
        return borrowRepository.findAll();
    }

    // ToDo make requests to other services to validate data
    public Optional<Borrow> createBorrow(Borrow borrow) {
        log.debug("creating borrow " + borrow);

        if (checkIfIdsAreValid(borrow.getBookId(), borrow.getUserId())) {
            Borrow createdBorrow = borrowRepository.save(borrow);
            borrowMetricService.processCreation(borrow);
            log.debug("created borrow " + createdBorrow);

            return Optional.of(createdBorrow);
        }
        return Optional.empty();
    }

    private boolean checkIfIdsAreValid(int bookId, int userId) {
        return bookService.checkIfBookExists(bookId) && userService.checkIfUserExists(userId);
    }
}
