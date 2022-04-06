package com.example.borrowservice.controller;

import com.example.borrowservice.models.dtos.BorrowDto;
import com.example.borrowservice.services.BorrowService;
import com.example.borrowservice.services.mapper.BorrowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
