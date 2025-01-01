package com.example.jwt.controller;

import com.example.jwt.dto.request.SaveBookRecordRequest;
import com.example.jwt.dto.response.RecordBookResponse;
import com.example.jwt.entity.User;
import com.example.jwt.service.RecordBookService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordBookController {

    private final RecordBookService recordBookService;
    private final UserService userService;

    //독서기록장 생성
    @PostMapping("/record")
    public ResponseEntity<Map<String, Object>> saveRecord(@RequestBody SaveBookRecordRequest request) {
        User currentUser = getCurrentUser();
        recordBookService.saveRecord(request, currentUser);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "BookRecord registered successfully");
        response.put("status", HttpStatus.CREATED.value());

        return ResponseEntity.ok(response);
    }

    //독서기록장 전체목록 조회
    @GetMapping("/record")
    public ResponseEntity<Map<String, Object>> findRecordBooks() {
        List<RecordBookResponse> recordBooks = recordBookService.findRecordBooks();

        if(recordBooks.isEmpty()) {
            // 응답 데이터 구성
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "독서기록이 존재하지 않습니다.");
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("recordBookList", recordBooks);
        response.put("status", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserId(authentication.getName());
    }
}