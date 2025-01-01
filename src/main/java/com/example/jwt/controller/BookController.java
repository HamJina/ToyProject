package com.example.jwt.controller;

import com.example.jwt.dto.BookDTO;
import com.example.jwt.dto.request.SaveBookRequest;
import com.example.jwt.dto.request.SearchTitleRequest;
import com.example.jwt.entity.Book;
import com.example.jwt.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //도서 등록하기
    @PostMapping("/book")
    public ResponseEntity<Map<String, Object>> saveBook(@RequestBody SaveBookRequest request) {
        bookService.saveBook(request);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book registered successfully");
        response.put("status", HttpStatus.CREATED.value());

        // 상태 코드 및 메시지 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //도서 전체 목록 조회
    @GetMapping("/book")
    public ResponseEntity<Map<String, Object>> findBooks() {
        List<Book> books = bookService.findBooks();

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("booksList", books);

        return ResponseEntity.ok(response);
    }

    //도서 이름으로 도서 검색
    @GetMapping("/book/search")
    public ResponseEntity<Map<String, Object>> searchBooks(@RequestParam String searchTitle) {
        List<BookDTO> bookDTOS = bookService.searhBooks(searchTitle);

        if(bookDTOS.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());
            errorResponse.put("message", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("searchedList", bookDTOS);
        System.out.println("searchedList" + bookDTOS);

        return ResponseEntity.ok(response);
    }

    //도서 상세 조회
    @GetMapping("/book/{id}")
    public ResponseEntity<Map<String, Object>> detailBook(@PathVariable Long id) {
        Book detailBook = bookService.detailBook(id);

        // 도서가 없을 경우 처리
        if (detailBook == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());
            errorResponse.put("message", "Book not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("details", detailBook);

        return ResponseEntity.ok(response);
    }


}
