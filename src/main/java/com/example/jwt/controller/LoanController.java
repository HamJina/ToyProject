package com.example.jwt.controller;


import com.example.jwt.dto.request.LoanBookRequest;
import com.example.jwt.dto.response.LoanResponse;
import com.example.jwt.entity.Loan;
import com.example.jwt.entity.User;
import com.example.jwt.service.LoanService;
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

    //대출목록조회
    @GetMapping("/loan")
    public ResponseEntity<Map<String, Object>> loanList() {
        User currentUser = getCurrentUser();
        List<LoanResponse> loanList = loanService.loanList(currentUser);

        if(loanList.isEmpty()) {
            // 응답 데이터 구성
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "독서기록이 존재하지 않습니다.");
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("loanList", loanList);
        response.put("status", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    //반납하기
    @DeleteMapping("/return")
    public ResponseEntity<Map<String, Object>> returnBook(@RequestParam Long loanId) {
        loanService.returnBook(loanId);

        // 응답 데이터 구성
        Map<String, Object> response = new HashMap<>();
        response.put("message", "반납되었습니다.");
        response.put("status", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByUserId(authentication.getName());
    }
}
