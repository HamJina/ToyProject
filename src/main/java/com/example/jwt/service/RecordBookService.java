package com.example.jwt.service;

import com.example.jwt.dto.request.SaveBookRecordRequest;
import com.example.jwt.dto.request.UpdateBookRecordRequest;
import com.example.jwt.dto.response.RecordBookDetail;
import com.example.jwt.dto.response.RecordBookResponse;
import com.example.jwt.entity.Book;
import com.example.jwt.entity.RecordBook;
import com.example.jwt.entity.User;
import com.example.jwt.repository.BookRepository;
import com.example.jwt.repository.RecordBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<RecordBookResponse> findRecordBooks() {
        List<RecordBook> recordBooks = recordBookRepository.findAll();

        LocalDate updatedDate = LocalDate.now();
        return recordBooks.stream().map((recordBook) ->
                new RecordBookResponse(
                        recordBook.getId(),
                        recordBook.getBook().getTitle(),
                        recordBook.getContent(),
                        updatedDate
                ))
                .collect(Collectors.toList());
    }

    public void deleteRecordBook(Long recordBookId) {
        RecordBook recordBook = recordBookRepository.findById(recordBookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 독서기록이 존재하지 않습니다."));
        recordBookRepository.delete(recordBook);
    }

    public RecordBookDetail detailRecordBook(Long recordBookId) {
        RecordBook recordBook = recordBookRepository.findById(recordBookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 독서기록이 존재하지 않습니다."));

        return new RecordBookDetail(recordBookId, recordBook.getBook().getTitle(), recordBook.getContent(), recordBook.getUpdatedDate(), recordBook.isLiked());
    }

    public void updateRecordBook(UpdateBookRecordRequest request) {
        RecordBook recordBook = recordBookRepository.findById(request.getRecordBookId())
                .orElseThrow(() -> new IllegalArgumentException("해당 독서기록이 존재하지 않습니다."));

        recordBook.setContent(request.getContent());
        LocalDate updatedDate = LocalDate.now();
        recordBook.setUpdatedDate(updatedDate);
    }
}
