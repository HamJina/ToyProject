package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user") // 테이블 이름 소문자로 변경
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 22, nullable = false) // 필수 값
    private String username;

    @Column(length = 22, nullable = false) // 필수 값
    private String name;

    @Column(length = 500, nullable = false) // 필수 값
    private String password;
}

