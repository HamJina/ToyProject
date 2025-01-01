package com.example.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
public class RecordBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_book_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String content;
    private LocalDate updatedDate;
    private boolean isLiked = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;

    public RecordBook(Book book, String content, LocalDate updatedDate, User createdBy) {
        this.book = book;
        this.content = content;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
    }
}
