package com.example.jwt.controller;

import com.example.jwt.dto.request.SaveBookRequest;
import com.example.jwt.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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



}
