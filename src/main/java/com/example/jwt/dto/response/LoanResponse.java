package com.example.jwt.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanResponse {

    private Long id;
    private String title;
    private String author;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanResponse(Long id,String title, String author, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}
