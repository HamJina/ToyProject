package com.example.jwt.controller;

import com.example.jwt.dto.LoginRequest;
import com.example.jwt.dto.UserDto;
import com.example.jwt.security.JwtTokenProvider;
import com.example.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody UserDto request) {
        System.out.println("userDto" + request.getUserId());
        userService.joinUser(request);

        //응답 메시지 생성
        Map<String, String> response = new HashMap<>();
        response.put("message", "회원가입 성공");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest request) {
        try {
            // 사용자 인증 시도
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword())
            );

            // 인증 성공 시 JWT 토큰 생성
            String jwtToken = jwtTokenProvider.createToken(authentication.getName());

            // 응답으로 토큰을 Map 형태로 반환
            Map<String, String> response = new HashMap<>();
            response.put("token", jwtToken);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse); // HTTP 401 응답
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<Map<String, String>> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        // 사용자 이름 가져오기
        String userName = userService.getUserNameByUserId(userDetails.getUsername());

        // 응답으로 사용자 이름을 반환
        Map<String, String> response = new HashMap<>();
        response.put("name", userName);

        return ResponseEntity.ok(response);
    }


}
