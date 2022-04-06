package com.example.borrowservice.services.mapper;

import com.example.borrowservice.models.Borrow;
import com.example.borrowservice.models.dtos.BorrowDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowMapper {

    public Borrow mapDtoToBorrow(BorrowDto borrowDto) {
        return Borrow.builder()
                .id(borrowDto.getId())
                .bookId(borrowDto.getBookId())
                .userId(borrowDto.getUserId())
                .build();
    }

    public List<Borrow> mapDtoListToBorrowList(List<BorrowDto> borrowDtos) {
        return borrowDtos.stream().map(this::mapDtoToBorrow).toList();
    }

    public BorrowDto mapBorrowToDto(Borrow borrow) {
        return BorrowDto.builder()
                .id(borrow.getId())
                .bookId(borrow.getBookId())
                .userId(borrow.getUserId())
                .build();
    }

    public List<BorrowDto> mapBorrowListToDtoList(List<Borrow> borrows) {
        return borrows.stream().map(this::mapBorrowToDto).toList();
    }
}
