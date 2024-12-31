package com.example.jwt.repository;

import com.example.jwt.dto.request.SaveBookRequest;
import com.example.jwt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
