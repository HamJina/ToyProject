package com.example.jwt.service;

import com.example.jwt.dto.request.SaveBookRecordRequest;
import com.example.jwt.entity.Book;
import com.example.jwt.entity.RecordBook;
import com.example.jwt.entity.User;
import com.example.jwt.repository.BookRepository;
import com.example.jwt.repository.RecordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordBookService {

    private final RecordBookRepository recordBookRepository;
    private final BookRepository bookRepository;

    public void saveRecord(SaveBookRecordRequest request, User currentUser) {
        // Book 조회 및 예외 처리
        Book findBook = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다."));

        // currentUser 유효성 검증
        if (currentUser == null) {
            throw new IllegalArgumentException("현재 사용자가 유효하지 않습니다.");
        }

        // RecordBook 생성 및 저장
        RecordBook recordBook = new RecordBook(findBook, request.getContent(), LocalDate.now(), currentUser);
        recordBookRepository.save(recordBook);
    }
}
