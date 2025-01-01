package com.example.jwt.dto;

import com.example.jwt.entity.Book;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {

    private String title;
    private String author;
    private String publisher;
    private String plot;
    private String category;
    private int bookNum;

    @QueryProjection
    public BookDTO(String title, String author, String publisher, String plot, String category, int bookNum) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.plot = plot;
        this.category = category;
        this.bookNum = bookNum;
    }
}
