package com.example.borrowservice.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    private int id;

    @Min(1)
    private int bookId;

    @Min(1)
    private int userId;
}
