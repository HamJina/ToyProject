package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "loans") // 테이블 이름 소문자로 변경
@NoArgsConstructor
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false) // Book과 단방향 관계
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // User와 단방향 관계
    private User user;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    public Loan(Book book, LocalDate loanDate, LocalDate returnDate, User user) {
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.user = user;
    }
}

