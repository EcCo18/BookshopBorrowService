package com.example.borrowservice.controller;

import com.example.borrowservice.models.Borrow;
import com.example.borrowservice.models.dtos.BorrowDto;
import com.example.borrowservice.services.BorrowService;
import com.example.borrowservice.services.mapper.BorrowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
@RequiredArgsConstructor
@Slf4j
public class BorrowController {

    private final BorrowService borrowService;
    private final BorrowMapper borrowMapper;

    @GetMapping()
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        log.info("received GET for all borrows");
        return ResponseEntity.ok(borrowMapper.mapBorrowListToDtoList(borrowService.getAllBorrows()));
    }

    @PostMapping()
    public ResponseEntity<BorrowDto> createBorrow(@Valid @RequestBody BorrowDto borrowDto) {
        log.info("received POST for borrow");
        Borrow createdBorrow = borrowService.createBorrow(borrowMapper.mapDtoToBorrow(borrowDto));
        return ResponseEntity.ok(borrowMapper.mapBorrowToDto(createdBorrow));
    }
}
