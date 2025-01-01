package com.example.jwt.controller;


import com.example.jwt.dto.request.LoanBookRequest;
import com.example.jwt.entity.User;
import com.example.jwt.service.LoanService;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;
    private final UserService userService;

    @PostMapping("/loan")
    public ResponseEntity<Map<String, Object>> loanBook(@RequestBody LoanBookRequest request) {
        User currentUser = getCurrentUser();
        loanService.loanBook(request, currentUser);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book registered successfully");
        response.put("status", HttpStatus.CREATED.value());

        return ResponseEntity.ok(response);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserId(authentication.getName());
    }
}
