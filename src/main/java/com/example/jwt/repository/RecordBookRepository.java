package com.example.jwt.repository;

import com.example.jwt.entity.RecordBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecordBookRepository extends JpaRepository<RecordBook, Long> {

}
