package com.example.jwt.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordBookResponse {

    private String bookTitle;
    private String content;
    private LocalDate updatedDate;

    public RecordBookResponse(String bookTitle, String content, LocalDate updatedDate) {
        this.bookTitle = bookTitle;
        this.content = content;
        this.updatedDate = updatedDate;
    }
}
