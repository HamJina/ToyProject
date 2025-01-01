package com.example.jwt.controller;

import com.example.jwt.dto.request.SaveBookRecordRequest;
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
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordBookController {

    private final RecordBookService recordBookService;
    private final UserService userService;

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

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserId(authentication.getName());
    }
}
