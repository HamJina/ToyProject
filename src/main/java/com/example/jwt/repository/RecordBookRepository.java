package com.example.jwt.repository;

import com.example.jwt.entity.RecordBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordBookRepository extends JpaRepository<RecordBook, Long> {
}
