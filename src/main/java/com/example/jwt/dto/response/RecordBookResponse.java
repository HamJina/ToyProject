package com.example.jwt.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordBookResponse {

    private Long recordBookId;
    private String bookTitle;
    private String content;
    private LocalDate updatedDate;

    public RecordBookResponse(Long recordBookId,String bookTitle, String content, LocalDate updatedDate) {
        this.recordBookId = recordBookId;
        this.bookTitle = bookTitle;
        this.content = content;
        this.updatedDate = updatedDate;
    }
}
