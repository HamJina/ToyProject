package com.example.jwt.service;

import com.example.jwt.dto.request.SaveBookRequest;
import com.example.jwt.entity.Book;
import com.example.jwt.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void saveBook(SaveBookRequest request) {
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (request.getAuthor() == null || request.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author is required.");
        }
        if (request.getPublisher() == null || request.getPublisher().isEmpty()) {
            throw new IllegalArgumentException("Author is required.");
        }
        bookRepository.save(new Book(request.getTitle(), request.getAuthor(), request.getPublisher(), request.getPlot(), request.getCategory(), request.getBookNum()));
    }

    public List<Book> findBooks() {
        return bookRepository.findAll();
    }

    public Book detailBook(Long bookId) {
        //해당 bookId로 DB에서 책 존재하는지 확인
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book with ID " + bookId + " not found"));

    }
}
