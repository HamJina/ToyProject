package com.example.jwt.repository;

import com.example.jwt.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    public List<Loan> findByUserId(Long userId);
}
