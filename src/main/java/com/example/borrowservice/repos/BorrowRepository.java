package com.example.borrowservice.repos;

import com.example.borrowservice.models.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
