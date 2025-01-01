package com.example.jwt.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordBookDetail {

    private Long recordBookId;
    private String bookTitle;
    private String content;
    private LocalDate updatedDate;
    private boolean isLiked;

    public RecordBookDetail(Long recordBookId, String bookTitle, String content, LocalDate updatedDate, boolean isLiked) {
        this.recordBookId = recordBookId;
        this.bookTitle = bookTitle;
        this.content = content;
        this.updatedDate = updatedDate;
        this.isLiked = isLiked;
    }
}
